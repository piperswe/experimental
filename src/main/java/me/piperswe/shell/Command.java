package me.piperswe.shell;

import lombok.NonNull;

import java.util.Arrays;
import java.util.List;

public interface Command {
    void run(@NonNull List<String> args);
    default void run(@NonNull String[] args) {
        run(Arrays.asList(args));
    }
}
