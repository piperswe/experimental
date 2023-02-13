package me.piperswe.demogui.kotlin

import javafx.scene.control.Label
import me.piperswe.utils.gui.ReactiveComponent
import me.piperswe.utils.gui.StateContainer

class LabelComponent(stateContainer: StateContainer<KtDemoState>) : ReactiveComponent<KtDemoState, Int, Label>(
    stateContainer
) {
    private var label: Label = Label()

    override fun render(): Label {
        return label
    }

    override fun select(state: KtDemoState): Int {
        return state.counter
    }

    override fun handleStateChange(newState: Int?, oldState: Int?) {
        val javaVersion = System.getProperty("java.version")
        val javafxVersion = System.getProperty("javafx.version")
        label.text = "Hello, JavaFX $javafxVersion (running on Java $javaVersion)! $newState click(s) so far."
    }
}