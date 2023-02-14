package me.piperswe.utils.gui;

import javafx.application.Platform;

public class JavaFXContext {
    private static final JavaFXContext global = new JavaFXContext();
    private boolean javafxStarted = false;

    private JavaFXContext() {
    }

    public static void startJavaFX() {
        global.startJavaFXImpl();
    }

    private synchronized void startJavaFXImpl() {
        if (!javafxStarted) {
            Platform.startup(() -> {
            });
            javafxStarted = true;
        }
    }
}
