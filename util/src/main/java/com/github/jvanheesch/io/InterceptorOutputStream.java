package com.github.jvanheesch.io;

import java.io.IOException;
import java.io.OutputStream;

/**
 * This class is purely method-forwarding boilerplate:
 * every method declared in InputStream is forwarded to underlyingInputStream.
 */
public class InterceptorOutputStream extends ForwardingOutputStream {
    private final OutputStream out;

    public InterceptorOutputStream(OutputStream underlyingOutputStream, OutputStream out) {
        super(underlyingOutputStream);

        this.out = out;
    }

    @Override
    public void write(byte[] b) throws IOException {
        super.write(b);

        this.out.write(b);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        super.write(b, off, len);

        this.out.write(b, off, len);
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
     * TODO_JORIS: see InterceptorInputStream
     * If out.close() is unwanted, it should be wrapped with {@link IOStreams#closeNoOpOutputStreamWrapper(OutputStream)}.
     */
    @Override
    public void close() throws IOException {
        super.close();

        this.out.flush();

        this.out.close();
    }
}
