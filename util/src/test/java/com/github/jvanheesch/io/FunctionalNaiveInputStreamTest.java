package com.github.jvanheesch.io;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class FunctionalNaiveInputStreamTest {

    @Test
    void basicExample() throws IOException {
        FunctionalNaiveInputStream functionalNaiveInputStream = new FunctionalNaiveInputStream(
                FunctionalNaiveInputStream.NextByteSupplier.ofIterator(
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

        byte[] result = new byte[10];
        functionalNaiveInputStream.read(result);

        assertThat(result)
                .isEqualTo(new byte[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9});

        assertThat(functionalNaiveInputStream.read())
                .isEqualTo(-1);
    }

}