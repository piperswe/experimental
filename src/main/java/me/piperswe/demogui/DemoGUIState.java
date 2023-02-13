package me.piperswe.demogui;

import lombok.NonNull;

public record DemoGUIState(int counter) {
    @NonNull public static DemoGUIState initialState() {
        return new DemoGUIState(0);
    }

    @NonNull public DemoGUIState increment() {
        return new DemoGUIState(counter + 1);
    }
}
