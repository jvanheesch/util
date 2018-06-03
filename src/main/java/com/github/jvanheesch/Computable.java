package com.github.jvanheesch;

import java.util.Optional;
import java.util.function.Supplier;

@FunctionalInterface
public interface Computable<R> {

    R compute() throws Throwable;

    static <R> R compute(Computable<R> computable) {
        try {
            return computable.compute();
        } catch (Throwable e) {
            throw new RuntimeException();
        }
    }

    static <R> Optional<R> computeSilently(Computable<R> computable) {
        try {
            return Optional.ofNullable(computable.compute());
        } catch (Throwable e) {
            return Optional.empty();
        }
    }

    static <R> Supplier<R> supplier(Computable<R> computable) {
        return () -> compute(computable);
    }
}
