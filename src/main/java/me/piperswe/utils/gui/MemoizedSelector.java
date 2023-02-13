package me.piperswe.utils.gui;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.NonNull;

public class MemoizedSelector<Obj, Selected> implements Selector<Obj, Selected> {
    @NonNull private final LoadingCache<Obj, Selected> cache;

    public MemoizedSelector(Selector<Obj, Selected> wrapped, boolean weakKeys, boolean softValues) {
        var builder = CacheBuilder.newBuilder();
        if (weakKeys) {
            builder = builder.weakKeys();
        }
        if (softValues) {
            builder = builder.softValues();
        }
        this.cache = builder.build(new CacheLoader<>() {
            @NonNull
            @Override
            public Selected load(@NonNull Obj obj) {
                return wrapped.select(obj);
            }
        });
    }

    public MemoizedSelector(Selector<Obj, Selected> wrapped) {
        this(wrapped, true, true);
    }

    @NonNull
    @Override
    public Selected select(@NonNull Obj obj) {
        return cache.getUnchecked(obj);
    }
}
