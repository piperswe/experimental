package me.piperswe.utils.gui;

import javafx.scene.Node;

public interface ReactiveComponentFactory<State, SelectedState, N extends Node> {
    ReactiveComponent<State, SelectedState, N> create(StateContainer<State> stateContainer);
}
