package me.piperswe.utils.gui;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;

import java.util.function.UnaryOperator;

public abstract class ReactiveComponent<State, SelectedState, N extends Node> implements Selector<State, SelectedState>, StateListener<SelectedState> {
    @Getter(AccessLevel.PROTECTED)
    @NonNull private final StateContainer<State> stateContainer;

    public ReactiveComponent(@NonNull StateContainer<State> stateContainer) {
        this.stateContainer = stateContainer;
        stateContainer.listen(new SelectedStateListener<>(this, new MemoizedSelector<>(this)));
    }

    protected void updateState(@NonNull UnaryOperator<State> updater) {
        stateContainer.update(updater);
    }

    protected <T extends Event> EventHandler<T> updateStateOnEvent(@NonNull UnaryOperator<State> updater) {
        return (event) -> updateState(updater);
    }

    public N mount() {
        N node = render();
        var selected = select(stateContainer.get());
        handleStateChange(selected, selected);
        return node;
    }

    protected abstract N render();
}
