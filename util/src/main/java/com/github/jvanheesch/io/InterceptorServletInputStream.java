package com.github.jvanheesch.io;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * All read methods, including readLine(), are built on the primitive read() method.
 */
public class InterceptorServletInputStream extends ServletInputStream {
    private final ServletInputStream in;
    private final OutputStream out;

    public InterceptorServletInputStream(ServletInputStream in, OutputStream out) {
        this.in = in;
        this.out = out;
    }

    @Override
    public long skip(long n) throws IOException {
        return this.in.skip(n);
    }

    @Override
    public int available() throws IOException {
        return this.in.available();
    }

    @Override
    public synchronized void mark(int readlimit) {
        this.in.mark(readlimit);
    }

    @Override
    public synchronized void reset() throws IOException {
        this.in.reset();
    }

    @Override
    public boolean markSupported() {
        return this.in.markSupported();
    }

    @Override
    public boolean isFinished() {
        return this.in.isFinished();
    }

    @Override
    public boolean isReady() {
        return this.in.isReady();
    }

    @Override
    public void setReadListener(ReadListener readListener) {
        this.in.setReadListener(readListener);
    }

    @Override
    public int read() throws IOException {
        int nextByteAsInt = this.in.read();

        if (nextByteAsInt != -1) {
            this.out.write(nextByteAsInt);
        }

        return nextByteAsInt;
    }

    @Override
    public void close() throws IOException {
        this.in.close();
        this.out.flush();
    }
}
