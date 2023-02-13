package me.piperswe.demogui.kotlin

import javafx.geometry.Pos
import javafx.scene.layout.VBox
import me.piperswe.utils.gui.ReactiveComponent
import me.piperswe.utils.gui.StateContainer

class KtDemoComponent(stateContainer: StateContainer<KtDemoState>) : ReactiveComponent<KtDemoState, Unit, VBox>(
    stateContainer
) {
    override fun select(obj: KtDemoState): Unit = Unit

    override fun handleStateChange(newState: Unit, oldState: Unit) = Unit

    override fun render(): VBox {
        val label = LabelComponent(stateContainer)
        val button = ButtonComponent(stateContainer)
        val box = VBox(label.mount(), button.mount())
        box.alignment = Pos.CENTER
        return box
    }
}