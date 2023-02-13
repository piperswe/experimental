package me.piperswe.demogui.kotlin

import javafx.scene.layout.VBox
import me.piperswe.demogui.DemoGUIApplication
import me.piperswe.shell.StageCommand
import me.piperswe.utils.gui.ReactiveApplication

class KtDemoApplication: ReactiveApplication<KtDemoState, Unit, VBox>(KtDemoState.initialState(), { s -> KtDemoComponent(s) }) {
    companion object {
        @JvmStatic
        fun getCommand(): StageCommand = StageCommand { KtDemoApplication() }
    }
}

fun main() {
    ReactiveApplication.launch(DemoGUIApplication::class.java)
}