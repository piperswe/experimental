package me.piperswe.utils.gui;

public interface MountableFactory<State> {
    Mountable create(StateContainer<State> stateContainer);
}
