package me.piperswe.utils.gui;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class RecordingStateListener<State> implements StateListener<State> {
    @Getter
    private final List<StatePair<State>> states = new ArrayList<>();

    @Override
    public void handleStateChange(State newState, State oldState) {
        states.add(new StatePair<>(newState, oldState));
    }
}
