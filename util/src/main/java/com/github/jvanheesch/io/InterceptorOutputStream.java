package com.github.jvanheesch.io;

import java.io.IOException;
import java.io.OutputStream;

public class InterceptorOutputStream extends SingleWriteForwardingOutputStream {
    private final OutputStream out;

    public InterceptorOutputStream(OutputStream underlyingOutputStream, OutputStream out) {
        super(underlyingOutputStream);

        this.out = out;
    }

    @Override
    public void write(int b) throws IOException {
        super.write(b);

        this.out.write(b);
    }

    @Override
    public void flush() throws IOException {
        super.flush();

        this.out.flush();
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
