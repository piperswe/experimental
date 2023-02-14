package me.piperswe.utils.gui;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.UnaryOperator;

@Slf4j
public class StateContainer<State> {
    private final AtomicReference<State> state;
    private final Set<StateListener<State>> listeners = ConcurrentHashMap.newKeySet();
    private boolean multithreaded = true;

    public StateContainer(State initialState) {
        this.state = new AtomicReference<>(initialState);
    }
    public StateContainer(State initialState, boolean multithreaded) {
        this.state = new AtomicReference<>(initialState);
        this.multithreaded = multithreaded;
    }

    public static <State> StateContainer<State> singleThreadOf(@NonNull State initialState) {
        return new StateContainer<>(initialState, false);
    }

    public State get() {
        return state.get();
    }

    private StatePair<State> performUpdate(UnaryOperator<State> updater) {
        AtomicReference<State> oldState = new AtomicReference<>(null);
        var newState = state.updateAndGet((state) -> {
            oldState.set(state);
            log.debug("running updater, passing state {}", state);
            var updatedState = updater.apply(state);
            log.debug("updater complete, created state {}", updatedState);
            return updatedState;
        });
        return new StatePair<>(newState, oldState.get());
    }

    private void notifyListeners(StatePair<State> states) {
        log.debug("notifying listeners");
        for (var listener : listeners) {
            log.debug("calling listener {}", listener.getClass().getName());
            listener.handleStateChange(states.newState(), states.oldState());
        }
        log.debug("all listeners notified");
    }

    public void update(UnaryOperator<State> updater) {
        if (multithreaded) {
            log.debug("updateState called - creating new virtual thread to update state");
            Thread.ofVirtual().name(String.format("%s State Updater", state.get().getClass().getName())).start(() -> {
                var states = performUpdate(updater);
                log.debug("queueing interface update job in UI thread");
                Platform.runLater(() -> notifyListeners(states));
            });
        } else {
            notifyListeners(performUpdate(updater));
        }
    }

    public void listen(@NonNull StateListener<State> listener) {
        this.listeners.add(listener);
    }

    public <T extends Event> EventHandler<T> updateOnEvent(@NonNull UnaryOperator<State> updater) {
        return (event) -> update(updater);
    }
}
