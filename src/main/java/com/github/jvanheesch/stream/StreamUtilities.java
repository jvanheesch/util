package com.github.jvanheesch.stream;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class StreamUtilities {
    public static <T> Stream<T> ofIterator(Iterator<T> sourceIterator) {
        return ofIterable(() -> sourceIterator, false);
    }

    private static <T> Stream<T> ofIterator(Iterator<T> sourceIterator, boolean parallel) {
        return ofIterable(() -> sourceIterator, parallel);
    }

    public static <T> Stream<T> ofIterable(Iterable<T> sourceIterable) {
        return ofIterable(sourceIterable, false);
    }

    private static <T> Stream<T> ofIterable(Iterable<T> sourceIterable, boolean parallel) {
        return StreamSupport.stream(sourceIterable.spliterator(), parallel);
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }
}
