package me.piperswe.demogui;

import javafx.scene.Node;
import javafx.scene.control.Button;
import lombok.NonNull;
import me.piperswe.utils.gui.ReactiveComponent;
import me.piperswe.utils.gui.StateContainer;
import org.jetbrains.annotations.NotNull;

public class ButtonComponent extends ReactiveComponent<DemoGUIState, DemoGUIState, Button> {
    private final Button button = new Button("Click me!");

    public ButtonComponent(@NonNull StateContainer<DemoGUIState> stateContainer) {
        super(stateContainer);
    }

    @Override
    public Button render() {
        button.setOnMouseClicked(updateStateOnEvent(DemoGUIState::increment));
        return button;
    }

    @NotNull
    @Override
    public DemoGUIState select(@NotNull DemoGUIState state) {
        return state;
    }

    @Override
    public void handleStateChange(DemoGUIState newState, DemoGUIState oldState) {

    }
}
