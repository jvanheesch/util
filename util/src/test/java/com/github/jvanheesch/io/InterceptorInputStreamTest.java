package com.github.jvanheesch.io;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.assertj.core.api.Java6Assertions.assertThat;

class InterceptorInputStreamTest {

    @Test
    void read1() throws IOException {
        String input = "someNonAsciiString€€€";

        try (
                ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                ByteArrayOutputStream captured = new ByteArrayOutputStream();
                InterceptorInputStream interceptor = new InterceptorInputStream(in, captured)
        ) {
            int byteRead;

            while ((byteRead = interceptor.read()) != -1) {
                out.write(byteRead);
            }

            assertThat(out.toByteArray())
                    .isEqualTo(captured.toByteArray())
                    .isEqualTo(input.getBytes());
        }
    }

    @Test
    void read2() throws IOException {
        String input = "someNonAsciiString€€€";

        try (
                ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                ByteArrayOutputStream captured = new ByteArrayOutputStream();
                InterceptorInputStream interceptor = new InterceptorInputStream(in, captured)
        ) {
            int nbOfBytesRead;

            byte[] buffer = new byte[4];

            while ((nbOfBytesRead = interceptor.read(buffer)) != -1) {
                out.write(buffer, 0, nbOfBytesRead);
            }

            assertThat(out.toByteArray())
                    .isEqualTo(captured.toByteArray())
                    .isEqualTo(input.getBytes());
        }
    }

    @Test
    void read3() throws IOException {
        String input = "someNonAsciiString€€€";

        try (
                ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                ByteArrayOutputStream captured = new ByteArrayOutputStream();
                InterceptorInputStream interceptor = new InterceptorInputStream(in, captured)
        ) {
            int nbOfBytesRead;

            byte[] buffer = new byte[8];

            while ((nbOfBytesRead = interceptor.read(buffer, 4, 4)) != -1) {
                out.write(buffer, 4, nbOfBytesRead);
            }

            assertThat(out.toByteArray())
                    .isEqualTo(captured.toByteArray())
                    .isEqualTo(input.getBytes());
        }
    }
}
