package me.piperswe.demogui;

import me.piperswe.shell.StageCommand;
import me.piperswe.utils.gui.ReactiveApplication;

public class DemoApp extends ReactiveApplication<State> {
    public DemoApp() {
        super(State.initialState(), RootComponent::new);
    }

    public static StageCommand getCommand() {
        return new StageCommand(DemoApp::new);
    }

    public static void main(String[] args) {
        launch(DemoApp.class);
    }
}
