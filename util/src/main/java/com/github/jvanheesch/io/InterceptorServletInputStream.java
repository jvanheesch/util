package com.github.jvanheesch.io;

import javax.servlet.ServletInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class InterceptorServletInputStream extends SingleReadForwardingServletInputStream {
    private final OutputStream out;

    public InterceptorServletInputStream(ServletInputStream underlyingInputStream, OutputStream out) {
        super(underlyingInputStream);

        this.out = out;
    }

    /**
     * All read methods, including readLine(), are built on top off the primitive read() method.
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
