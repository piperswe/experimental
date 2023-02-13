package me.piperswe.demogui.kotlin

data class KtDemoState(val counter: Int) {
    companion object {
        fun initialState(): KtDemoState = KtDemoState(0)
    }

    fun increment(): KtDemoState = KtDemoState(counter + 1)
}
