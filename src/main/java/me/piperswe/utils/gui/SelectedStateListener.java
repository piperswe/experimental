package me.piperswe.utils.gui;

import lombok.NonNull;

public class SelectedStateListener<State, SelectedState> implements StateListener<State> {
    @NonNull private final StateListener<SelectedState> listener;
    @NonNull private final Selector<State, SelectedState> selector;

    public SelectedStateListener(@NonNull StateListener<SelectedState> listener, @NonNull Selector<State, SelectedState> selector) {
        this.listener = listener;
        this.selector = selector;
    }

    @Override
    public void handleStateChange(State newState, State oldState) {
        var newSelectedState = selector.select(newState);
        var oldSelectedState = selector.select(oldState);
        if (!newSelectedState.equals(oldSelectedState)) {
            listener.handleStateChange(newSelectedState, oldSelectedState);
        }
    }
}
