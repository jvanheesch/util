package com.github.jvanheesch.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class InterceptorInputStream extends SingleReadForwardingInputStream {
    private final OutputStream out;

    public InterceptorInputStream(InputStream underlyingInputStream, OutputStream out) {
        super(underlyingInputStream);

        this.out = out;
    }

    /**
     * All read methods are built on top off the primitive read() method.
     */
    @Override
    public int read() throws IOException {
        int nextByteAsInt = super.read();

        if (nextByteAsInt != -1) {
            this.out.write(nextByteAsInt);
        }

        return nextByteAsInt;
    }

    /**
     * If out.close() is unwanted, it should be wrapped with {@link IOStreams#closeNoOpOutputStreamWrapper(OutputStream)}.
     */
    @Override
    public void close() throws IOException {
        super.close();

        this.out.flush();

        this.out.close();
    }
}
