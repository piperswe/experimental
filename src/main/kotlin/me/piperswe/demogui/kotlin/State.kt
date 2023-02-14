package me.piperswe.demogui.kotlin

data class State(val counter: Int) {
    fun increment(): State = State(counter + 1)
}

val initialState = State(0)