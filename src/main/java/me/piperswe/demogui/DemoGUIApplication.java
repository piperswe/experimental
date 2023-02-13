package me.piperswe.demogui;

import javafx.scene.layout.VBox;
import me.piperswe.shell.StageCommand;
import me.piperswe.utils.gui.ReactiveApplication;

public class DemoGUIApplication extends ReactiveApplication<DemoGUIState, Integer, VBox> {
    public DemoGUIApplication() {
        super(DemoGUIState.initialState(), DemoGUIComponent::new);
    }

    public static StageCommand getCommand() {
        return new StageCommand(DemoGUIApplication::new);
    }

    public static void main(String[] args) {
        launch(DemoGUIApplication.class);
    }
}
