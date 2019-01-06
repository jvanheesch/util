package com.github.jvanheesch.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class InterceptorInputStream extends InputStream {
    private final InputStream in;
    private final OutputStream out;

    public InterceptorInputStream(InputStream in, OutputStream out) {
        this.in = in;
        this.out = out;
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

    /**
     * Close should probably close the inputstream, as it serves as a proxy/substitute.
     * It should probably not close the ouputstream, as that might render it useless.
     */
    @Override
    public void close() throws IOException {
        this.in.close();
        // TODO_JORIS: might make more sense to flush on each ead.
        this.out.flush();
    }
}
