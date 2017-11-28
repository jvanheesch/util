package com.mycompany.stream;

import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public final class CollectorFactory {
    private CollectorFactory() {
    }

    public static <T> Collector<T, ?, T> toUniqueElement() {
        return Collectors.collectingAndThen(
                toUniqueOptional(),
                optionalResult -> optionalResult.<IllegalStateException>orElseThrow(() -> {
                    throw new IllegalStateException("The stream was empty!");
                })
        );
    }

    public static <T> Collector<T, ?, Optional<T>> toUniqueOptional() {
        return Collectors.reducing((a, b) -> {
            throw new IllegalStateException("The stream contained more than 1 element!");
        });
    }
}
