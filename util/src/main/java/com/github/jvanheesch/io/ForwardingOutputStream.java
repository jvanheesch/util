package com.github.jvanheesch.io;

import java.io.IOException;
import java.io.OutputStream;

public class ForwardingOutputStream extends OutputStream {
    private final OutputStream underlyingOutputStream;

    public ForwardingOutputStream(OutputStream underlyingOutputStream) {
        this.underlyingOutputStream = underlyingOutputStream;
    }

    @Override
    public void write(byte[] b) throws IOException {
        this.underlyingOutputStream.write(b);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        this.underlyingOutputStream.write(b, off, len);
    }

    @Override
    public void write(int b) throws IOException {
        this.underlyingOutputStream.write(b);
    }

    @Override
    public void flush() throws IOException {
        this.underlyingOutputStream.flush();
    }

    @Override
    public void close() throws IOException {
        this.underlyingOutputStream.close();
    }
}
