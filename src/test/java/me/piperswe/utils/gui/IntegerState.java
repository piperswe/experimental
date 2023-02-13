package me.piperswe.utils.gui;

import lombok.NonNull;

public record IntegerState(int i) {
    public static final IntegerState initialState = new IntegerState(0);

    @NonNull public IntegerState increment() {
        return new IntegerState(i + 1);
    }

    @NonNull public IntegerState decrement() {
        return new IntegerState(i - 1);
    }
}
