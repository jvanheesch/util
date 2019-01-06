package com.github.jvanheesch.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * Methods {@link InputStream#read(byte[])} and {@link InputStream#read(byte[], int, int)} are NOT overridden.
 * This causes ALL read calls to pass {@link InputStream#read(byte[])}
 */
public class SingleReadForwardingInputStream extends InputStream {
    private final InputStream underlyingInputStream;

    public SingleReadForwardingInputStream(InputStream underlyingInputStream) {
        this.underlyingInputStream = underlyingInputStream;
    }

    @Override
    public final int read(byte[] b) throws IOException {
        return super.read(b);
    }

    @Override
    public final int read(byte[] b, int off, int len) throws IOException {
        return super.read(b, off, len);
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
