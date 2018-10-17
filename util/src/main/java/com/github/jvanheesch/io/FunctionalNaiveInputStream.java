package com.github.jvanheesch.io;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.stream.Stream;

public class FunctionalNaiveInputStream extends InputStream {
    private final NextByteSupplier nextByteSupplier;

    public FunctionalNaiveInputStream(NextByteSupplier nextByteSupplier) {
        this.nextByteSupplier = nextByteSupplier;
    }

    @Override
    public int read() throws IOException {
        return this.nextByteSupplier.read();
    }

    @FunctionalInterface
    public interface NextByteSupplier {
        int read() throws IOException;

        static NextByteSupplier ofIterator(Iterator<Byte> iterator) {
            return () -> iterator.hasNext() ? iterator.next() : -1;
        }
    }

    public static void main(String[] args) throws IOException {
        FunctionalNaiveInputStream functionalNaiveInputStream = new FunctionalNaiveInputStream(
                NextByteSupplier.ofIterator(
                        Stream
                                .iterate(
                                        0,
                                        n -> n + 1
                                )
                                .limit(10)
                                .map(i -> (byte) i.intValue())
                                .iterator()
                )
        );

        for (int i = 0; i < 20; i++) {
            System.out.println(functionalNaiveInputStream.read());
        }
    }
}
