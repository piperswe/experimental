package me.piperswe.utils.gui;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.*;

class MemoizedSelectorTest {
    @Test
    void alwaysRunsForDifferentInput() {
        var ran = new AtomicBoolean(false);
        var selector = new MemoizedSelector<IntegerState, Integer>((state) -> {
            ran.set(true);
            return state.i();
        });

        var state = IntegerState.initialState;
        var selected = selector.select(state);
        assertTrue(ran.get());
        assertEquals(0, selected);
        ran.set(false);

        state = state.increment();
        selected = selector.select(state);
        assertTrue(ran.get());
        assertEquals(1, selected);
    }

    @Test
    void doesntRunForSameInput() {
        var ran = new AtomicBoolean(false);
        var selector = new MemoizedSelector<IntegerState, Integer>((state) -> {
            ran.set(true);
            return state.i();
        }, false, false);

        var state = IntegerState.initialState;
        var selected = selector.select(state);
        assertTrue(ran.get());
        assertEquals(0, selected);
        ran.set(false);
        selected = selector.select(state);
        assertFalse(ran.get());
        assertEquals(0, selected);
        ran.set(false);

        state = state.increment();
        selected = selector.select(state);
        assertTrue(ran.get());
        assertEquals(1, selected);
        ran.set(false);

        // Go back to 0 - should be cached
        state = state.decrement();
        selected = selector.select(state);
        assertFalse(ran.get());
        assertEquals(0, selected);
    }
}