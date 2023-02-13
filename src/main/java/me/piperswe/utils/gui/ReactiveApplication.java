package me.piperswe.utils.gui;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.UnaryOperator;

@Slf4j public class ReactiveApplication<State, SelectedState, Root extends Parent> implements MiniApplication {
    @NonNull private final ReactiveComponent<State, SelectedState, Root> rootComponent;

    protected ReactiveApplication(@NonNull State initialState, @NonNull ReactiveComponentFactory<State, SelectedState, Root> rootComponentFactory) {
        @NonNull StateContainer<State> state = new StateContainer<>(initialState);
        this.rootComponent = rootComponentFactory.create(state);
    }

    @Override
    public void start(@NonNull Stage stage) {
        log.debug("Starting application");
        var rootNode = rootComponent.render();
        Scene scene = new Scene(rootNode, 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static <State, SelectedState, N extends Parent, App extends ReactiveApplication<State, SelectedState, N>> void launch(Class<App> klass) {
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
}
