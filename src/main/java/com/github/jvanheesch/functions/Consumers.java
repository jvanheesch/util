package com.github.jvanheesch.functions;

import java.io.Serializable;
import java.util.function.Consumer;

public final class Consumers {
    public static <T> Consumer<T> noop() {
        return (Consumer<T> & Serializable) t -> {
        };
    }
}
