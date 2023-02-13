package me.piperswe.utils.gui;

import lombok.NonNull;

public interface Selector<Obj, Selected> {
    @NonNull Selected select(@NonNull Obj obj);
}
