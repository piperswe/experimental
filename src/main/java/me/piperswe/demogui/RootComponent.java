package me.piperswe.demogui;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import lombok.NonNull;
import me.piperswe.utils.gui.StateContainer;
import me.piperswe.utils.gui.StaticComponent;

public class RootComponent extends StaticComponent<State> {
    public RootComponent(@NonNull StateContainer<State> stateContainer) {
        super(stateContainer);
    }

    @Override
    public VBox render() {
        var label = new LabelComponent(getStateContainer());
        var button = new ButtonComponent(getStateContainer());
        var box = new VBox(label.mount(), button.mount());
        box.setAlignment(Pos.CENTER);
        return box;
    }
}
