package com.github.jvanheesch.io;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class InterceptorServletOutputStream extends SingleWriteForwardingServletOutputStream {
    private final OutputStream out;

    public InterceptorServletOutputStream(ServletOutputStream underlyingServletOutputStream, OutputStream out) {
        super(underlyingServletOutputStream);

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
    }
}
