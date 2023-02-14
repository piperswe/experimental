package me.piperswe.demogui.kotlin

import javafx.scene.control.Label
import me.piperswe.utils.gui.createReactiveComponentFactory

val labelFactory = createReactiveComponentFactory<State, Int, Label>(
    { Label() },
    { it.counter },
    { counter, _, label ->
        val javaVersion = System.getProperty("java.version")
        val javafxVersion = System.getProperty("javafx.version")
        label.text =
            "Hello, JavaFX $javafxVersion (running on Java $javaVersion, Kotlin ${KotlinVersion.CURRENT})! $counter click(s) so far."
    }
)