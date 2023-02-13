package me.piperswe.utils;

import com.google.common.util.concurrent.AbstractExecutionThreadService;
import lombok.NonNull;

import java.util.concurrent.Executor;

public abstract class AbstractVirtualExecutionThreadService extends AbstractExecutionThreadService {
    @Override
    @NonNull
    protected Executor executor() {
        return command -> Thread.ofVirtual().name(serviceName()).start(command);
    }
}
