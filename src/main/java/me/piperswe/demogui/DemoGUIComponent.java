package me.piperswe.demogui;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import lombok.NonNull;
import me.piperswe.utils.gui.ReactiveComponent;
import me.piperswe.utils.gui.StateContainer;
import org.jetbrains.annotations.NotNull;

public class DemoGUIComponent extends ReactiveComponent<DemoGUIState, Integer, VBox> {
    public DemoGUIComponent(@NonNull StateContainer<DemoGUIState> stateContainer) {
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

    @NotNull
    @Override
    public Integer select(@NotNull DemoGUIState demoGUIState) {
        return 0;
    }

    @Override
    public void handleStateChange(Integer newState, Integer oldState) {

    }
}
