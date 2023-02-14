package me.piperswe.demogui.kotlin

import javafx.scene.control.Button
import me.piperswe.utils.gui.createStaticComponentFactory

val buttonFactory = createStaticComponentFactory<State> { sc ->
    val button = Button("Click me!")
    button.onMouseClicked = sc.updateOnEvent(State::increment)
    button
}