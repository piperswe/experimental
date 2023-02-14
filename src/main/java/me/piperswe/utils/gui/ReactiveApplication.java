package me.piperswe.utils.gui;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReactiveApplication<State> implements MiniApplication {
    @NonNull
    private final Mountable rootComponent;

    public ReactiveApplication(@NonNull State initialState, @NonNull MountableFactory<State> rootComponentFactory) {
        @NonNull StateContainer<State> state = new StateContainer<>(initialState);
        this.rootComponent = rootComponentFactory.create(state);
    }

    public static <State, App extends ReactiveApplication<State>> void launch(Class<App> klass) {
        Platform.startup(() -> {
            var stage = new Stage();
            try {
                var app = klass.getDeclaredConstructor().newInstance();
                app.start(stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void start(@NonNull Stage stage) {
        log.debug("Starting application");
        var rootNode = rootComponent.mount();
        Scene scene = new Scene(rootNode, 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public void launch() {
        Platform.startup(() -> {
            var stage = new Stage();
            start(stage);
        });
    }
}
