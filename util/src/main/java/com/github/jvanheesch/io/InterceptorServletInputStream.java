package com.github.jvanheesch.io;

import com.github.jvanheesch.Executable;

import javax.servlet.ServletInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class InterceptorServletInputStream extends ForwardingServletInputStream {
    private final OutputStream out;
    private final Executable onCloseCallback;

    public InterceptorServletInputStream(
            ServletInputStream underlyingInputStream,
            OutputStream out,
            Executable onCloseCallback
    ) {
        super(underlyingInputStream);

        this.out = out;
        this.onCloseCallback = onCloseCallback;
    }

    @Override
    public int readLine(byte[] b, int off, int len) throws IOException {
        int nbOfBytesRead = super.readLine(b, off, len);

        if (nbOfBytesRead != -1) {
            // write the bytes, read into b, to out
            this.out.write(b, 0, nbOfBytesRead);
        }

        return nbOfBytesRead;
    }

    @Override
    public int read(byte[] b) throws IOException {
        int nbOfBytesRead = super.read(b);

        if (nbOfBytesRead != -1) {
            // write the bytes, read into b, to out
            this.out.write(b, 0, nbOfBytesRead);
        }

        return nbOfBytesRead;
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        int nbOfBytesRead = super.read(b, off, len);

        if (nbOfBytesRead != -1) {
            // write the bytes, read into b, to out
            this.out.write(b, off, nbOfBytesRead);
        }

        return nbOfBytesRead;
    }

    @Override
    public int read() throws IOException {
        int nextByteAsInt = super.read();

        if (nextByteAsInt != -1) {
            this.out.write(nextByteAsInt);
        }

        return nextByteAsInt;
    }

    @Override
    public void close() throws IOException {
        super.close();

        this.onCloseCallback.executeSilently();
    }
}
