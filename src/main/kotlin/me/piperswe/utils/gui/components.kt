package me.piperswe.utils.gui

import javafx.scene.Parent

fun <State : Any> createStaticComponentFactory(renderer: (StateContainer<State>) -> Parent): MountableFactory<State> =
    MountableFactory<State> { stateContainer ->
        object : StaticComponent<State>(stateContainer) {
            override fun render(): Parent = renderer(stateContainer)
        }
    }

fun <State : Any, SelectedState : Any, N : Parent> createReactiveComponentFactory(
    renderer: (StateContainer<State>) -> N,
    selector: (State) -> SelectedState,
    updater: (SelectedState, SelectedState, N) -> Unit
): MountableFactory<State> =
    MountableFactory { stateContainer ->
        object : ReactiveComponent<State, SelectedState>(stateContainer) {
            private var n: N? = null

            override fun render(): Parent {
                val rendered = renderer(stateContainer)
                n = rendered
                return rendered
            }

            override fun handleStateChange(newState: SelectedState, oldState: SelectedState) {
                n?.let {
                    updater(newState, oldState, it)
                }
            }

            override fun select(state: State): SelectedState = selector(state)

        }
    }