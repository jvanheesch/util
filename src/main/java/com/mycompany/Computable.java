package com.mycompany;

import java.util.Optional;

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
}
