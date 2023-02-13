package me.piperswe.utils.gui;

import lombok.NonNull;

public record StatePair<State>(@NonNull State newState, @NonNull State oldState) {
}
