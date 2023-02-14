package me.piperswe.demogui.kotlin

import javafx.geometry.Pos
import javafx.scene.layout.VBox
import me.piperswe.utils.gui.createStaticComponentFactory

val rootFactory = createStaticComponentFactory<State> { sc ->
    val label = labelFactory.create(sc)
    val button = buttonFactory.create(sc)
    val box = VBox(label.mount(), button.mount())
    box.alignment = Pos.CENTER
    box
}