package me.piperswe.demogui;

import lombok.NonNull;

public record State(int counter) {
    @NonNull
    public static State initialState() {
        return new State(0);
    }

    @NonNull
    public State increment() {
        return new State(counter + 1);
    }
}
