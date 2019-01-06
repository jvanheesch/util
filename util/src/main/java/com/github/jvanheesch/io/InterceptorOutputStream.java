package com.github.jvanheesch.io;

import java.io.IOException;
import java.io.OutputStream;

public class InterceptorOutputStream extends OutputStream {
    private final OutputStream original;
    private final OutputStream captured;

    public InterceptorOutputStream(OutputStream original, OutputStream captured) {
        this.original = original;
        this.captured = captured;
    }

    @Override
    public void write(int b) throws IOException {
        this.original.write(b);
        this.captured.write(b);
    }

    @Override
    public void flush() throws IOException {
        this.original.flush();
        this.captured.flush();
    }

    /**
     * Close should probably close the original outputstream, as it serves as a proxy/substitute.
     * It should probably not close the other outputstream, as that might render it useless.
     */
    @Override
    public void close() throws IOException {
        this.original.close();
    }
}
