package me.piperswe.utils.gui;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;

import java.util.function.UnaryOperator;

public abstract class StaticComponent<State> implements Mountable, Renderable {
    @Getter(AccessLevel.PROTECTED)
    @NonNull
    private final StateContainer<State> stateContainer;

    public StaticComponent(@NonNull StateContainer<State> stateContainer) {
        this.stateContainer = stateContainer;
    }

    protected void updateState(@NonNull UnaryOperator<State> updater) {
        getStateContainer().update(updater);
    }

    protected <T extends Event> EventHandler<T> updateStateOnEvent(@NonNull UnaryOperator<State> updater) {
        return (event) -> updateState(updater);
    }

    @Override
    public Parent mount() {
        return render();
    }
}
