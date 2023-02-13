package me.piperswe.utils.gui;

import javafx.stage.Stage;
import lombok.NonNull;

public interface MiniApplication {
    void start(@NonNull Stage stage) throws Exception;
}
