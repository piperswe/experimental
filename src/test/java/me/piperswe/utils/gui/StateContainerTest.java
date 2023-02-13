package me.piperswe.utils.gui;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StateContainerTest {
    @Test
    void storesValue() {
        var container = StateContainer.singleThreadOf("hello, world!");
        assertEquals("hello, world!", container.get());
    }

    @Test
    void storesAndUpdatesValue() {
        var container = StateContainer.singleThreadOf(IntegerState.initialState);
        assertEquals(new IntegerState(0), container.get());
        container.update(IntegerState::increment);
        assertEquals(new IntegerState(1), container.get());
        container.update(IntegerState::decrement);
        assertEquals(new IntegerState(0), container.get());
    }

    @Test
    void notifiesListeners() {
        var listenerA = new RecordingStateListener<IntegerState>();
        var listenerB = new RecordingStateListener<IntegerState>();
        assertEquals(0, listenerA.getStates().size());
        assertEquals(0, listenerB.getStates().size());
        var container = StateContainer.singleThreadOf(IntegerState.initialState);
        container.listen(listenerA);
        container.listen(listenerB);

        container.update(IntegerState::increment);
        assertEquals(new IntegerState(1), container.get());
        assertEquals(1, listenerA.getStates().size());
        assertEquals(new StatePair<>(new IntegerState(1), new IntegerState(0)), listenerA.getStates().get(0));
        assertEquals(1, listenerB.getStates().size());
        assertEquals(new StatePair<>(new IntegerState(1), new IntegerState(0)), listenerB.getStates().get(0));

        container.update(IntegerState::decrement);
        assertEquals(new IntegerState(0), container.get());
        assertEquals(2, listenerA.getStates().size());
        assertEquals(new StatePair<>(new IntegerState(0), new IntegerState(1)), listenerA.getStates().get(1));
        assertEquals(2, listenerB.getStates().size());
        assertEquals(new StatePair<>(new IntegerState(0), new IntegerState(1)), listenerB.getStates().get(1));
    }
}