package me.piperswe.demogui.kotlin

import me.piperswe.shell.StageCommand
import me.piperswe.utils.gui.ReactiveApplication

fun createDemoApplication() = ReactiveApplication(initialState, rootFactory)
val demoApplicationCommand = StageCommand { createDemoApplication() }

fun main() {
    createDemoApplication().launch()
}