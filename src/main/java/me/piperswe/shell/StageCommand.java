package me.piperswe.shell;

import javafx.application.Platform;
import javafx.stage.Stage;
import lombok.NonNull;
import me.piperswe.utils.gui.MiniApplication;

import java.util.List;
import java.util.function.Supplier;

public class StageCommand implements Command {
    private static boolean javafxStarted = false;

    private final @NonNull Supplier<MiniApplication> applicationGenerator;

    public StageCommand(@NonNull Supplier<MiniApplication> applicationGenerator) {
        this.applicationGenerator = applicationGenerator;
    }

    @Override
    public void run(@NonNull List<String> args) {
        Runnable onStart = () -> {
            Platform.setImplicitExit(false);
            var app = applicationGenerator.get();
            var stage = new Stage();
            try {
                app.start(stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
        synchronized (StageCommand.class) {
            if (!javafxStarted) {
                Platform.startup(onStart);
                javafxStarted = true;
            } else {
                Platform.runLater(onStart);
            }
        }
    }
}
