package com.github.jvanheesch.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * This class is purely method-forwarding boilerplate:
 * every method declared in InputStream is forwarded to underlyingInputStream.
 */
public class ForwardingInputStream extends InputStream {
    private final InputStream underlyingInputStream;

    public ForwardingInputStream(InputStream underlyingInputStream) {
        this.underlyingInputStream = underlyingInputStream;
    }

    @Override
    public int read(byte[] b) throws IOException {
        return this.underlyingInputStream.read(b);
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        return this.underlyingInputStream.read(b, off, len);
    }

    @Override
    public int read() throws IOException {
        return this.underlyingInputStream.read();
    }

    @Override
    public long skip(long n) throws IOException {
        return this.underlyingInputStream.skip(n);
    }

    @Override
    public int available() throws IOException {
        return this.underlyingInputStream.available();
    }

    @Override
    public synchronized void mark(int readlimit) {
        this.underlyingInputStream.mark(readlimit);
    }

    @Override
    public boolean markSupported() {
        return this.underlyingInputStream.markSupported();
    }

    @Override
    public synchronized void reset() throws IOException {
        this.underlyingInputStream.reset();
    }

    @Override
    public void close() throws IOException {
        this.underlyingInputStream.close();
    }
}
