package me.piperswe.utils.gui

import javafx.scene.control.Button
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class CreateStaticComponentFactoryTest {
    class ButtonWithStateContainer(val sc: StateContainer<Unit>) : Button()

    @Test
    fun passesThroughNode() {
        JavaFXContext.startJavaFX()
        val button = Button("My Button")
        val factory = createStaticComponentFactory<Unit> { button }
        val component = factory.create(StateContainer(Unit))
        val buttonFromComponent = component.mount()
        assertEquals(button, buttonFromComponent)
        assertEquals("My Button", (buttonFromComponent as Button).text)
    }

    @Test
    fun passesThroughStateContainer() {
        JavaFXContext.startJavaFX()
        val stateContainer = StateContainer(Unit)
        val factory = createStaticComponentFactory { sc -> ButtonWithStateContainer(sc) }
        val component = factory.create(stateContainer)
        val buttonFromComponent = component.mount()
        assertTrue(buttonFromComponent is ButtonWithStateContainer)
        assertEquals(stateContainer, (buttonFromComponent as ButtonWithStateContainer).sc)
    }
}