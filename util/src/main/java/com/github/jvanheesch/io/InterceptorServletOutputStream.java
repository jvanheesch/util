package com.github.jvanheesch.io;

import com.github.jvanheesch.Executable;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class InterceptorServletOutputStream extends ForwardingServletOutputStream {
    private final OutputStream out;
    private final Executable onCloseCallback;

    public InterceptorServletOutputStream(
            ServletOutputStream underlyingServletOutputStream,
            OutputStream out,
            Executable onCloseCallback
    ) {
        super(underlyingServletOutputStream);

        this.out = out;
        this.onCloseCallback = onCloseCallback;
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

    @Override
    public void close() throws IOException {
        super.close();

        this.onCloseCallback.executeSilently();
    }
}
