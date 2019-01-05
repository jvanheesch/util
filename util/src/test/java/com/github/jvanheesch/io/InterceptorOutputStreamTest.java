package com.github.jvanheesch.io;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.assertj.core.api.Java6Assertions.assertThat;

class InterceptorOutputStreamTest {
    @Test
    void write1() throws IOException {
        String input = "someNonAsciiString€€€";

        try (
                ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                ByteArrayOutputStream captured = new ByteArrayOutputStream();
                InterceptorOutputStream interceptor = new InterceptorOutputStream(out, captured)
        ) {
            int byteRead;

            while ((byteRead = in.read()) != -1) {
                interceptor.write(byteRead);
            }

            assertThat(out.toByteArray())
                    .isEqualTo(captured.toByteArray())
                    .isEqualTo(input.getBytes());
        }
    }
}
