package me.piperswe.demogui.kotlin

import javafx.scene.control.Button
import me.piperswe.utils.gui.ReactiveComponent
import me.piperswe.utils.gui.StateContainer

class ButtonComponent(stateContainer: StateContainer<KtDemoState>) : ReactiveComponent<KtDemoState, Unit, Button>(
    stateContainer
) {
    override fun select(obj: KtDemoState) = Unit

    override fun handleStateChange(newState: Unit, oldState: Unit) = Unit

    override fun render(): Button {
        val button = Button("Click me!")
        button.onMouseClicked = updateStateOnEvent(KtDemoState::increment)
        return button
    }
}