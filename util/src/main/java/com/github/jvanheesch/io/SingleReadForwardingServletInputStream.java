package com.github.jvanheesch.io;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import java.io.IOException;

public class SingleReadForwardingServletInputStream extends ServletInputStream {
    private final ServletInputStream underlyingInputStream;

    public SingleReadForwardingServletInputStream(ServletInputStream underlyingInputStream) {
        this.underlyingInputStream = underlyingInputStream;
    }

    @Override
    public final int readLine(byte[] b, int off, int len) throws IOException {
        return super.readLine(b, off, len);
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
    public boolean isFinished() {
        return this.underlyingInputStream.isFinished();
    }

    @Override
    public boolean isReady() {
        return this.underlyingInputStream.isReady();
    }

    @Override
    public void setReadListener(ReadListener readListener) {
        this.underlyingInputStream.setReadListener(readListener);
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
