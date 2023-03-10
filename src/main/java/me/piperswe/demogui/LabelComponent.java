package me.piperswe.demogui;

import javafx.scene.control.Label;
import lombok.NonNull;
import me.piperswe.utils.gui.ReactiveComponent;
import me.piperswe.utils.gui.StateContainer;
import org.jetbrains.annotations.NotNull;

public class LabelComponent extends ReactiveComponent<State, Integer> {
    private final Label label = new Label();

    public LabelComponent(@NonNull StateContainer<State> stateContainer) {
        super(stateContainer);
    }

    @Override
    public Label render() {
        return label;
    }

    @NotNull
    @Override
    public Integer select(@NotNull State state) {
        return state.counter();
    }

    @Override
    public void handleStateChange(Integer newState, Integer oldState) {
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        label.setText(String.format(
                "Hello, JavaFX %s (running on Java %s)! %d click(s) so far.",
                javafxVersion, javaVersion, newState
        ));
    }
}
