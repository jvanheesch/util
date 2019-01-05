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

    /**
     * Close should probably close the inputstream, as it serves as a proxy/substitute.
     * It should probably not close the ouputstream, as that might render it useless.
     */
    @Override
    public void close() throws IOException {
        this.in.close();
    }
}
