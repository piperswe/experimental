package me.piperswe.demogui;

import javafx.scene.control.Button;
import lombok.NonNull;
import me.piperswe.utils.gui.StateContainer;
import me.piperswe.utils.gui.StaticComponent;

public class ButtonComponent extends StaticComponent<State> {
    private final Button button = new Button("Click me!");

    public ButtonComponent(@NonNull StateContainer<State> stateContainer) {
        super(stateContainer);
    }

    @Override
    public Button render() {
        button.setOnMouseClicked(updateStateOnEvent(State::increment));
        return button;
    }
}
