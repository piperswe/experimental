package me.piperswe.utils.gui;

public interface StateListener<State> {
    void handleStateChange(State newState, State oldState);
}
