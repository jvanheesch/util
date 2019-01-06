package com.github.jvanheesch.io;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import java.io.IOException;
import java.io.OutputStream;

public class InterceptorServletOutputStream extends ServletOutputStream {
    private final ServletOutputStream original;
    private final OutputStream captured;

    public InterceptorServletOutputStream(ServletOutputStream original, OutputStream captured) {
        this.original = original;
        this.captured = captured;
    }

    @Override
    public void write(int b) throws IOException {
        this.original.write(b);
        this.captured.write(b);
    }

    @Override
    public void flush() throws IOException {
        this.original.flush();
        this.captured.flush();
    }

    @Override
    public void print(String s) throws IOException {
        this.original.print(s);
    }

    @Override
    public void print(boolean b) throws IOException {
        this.original.print(b);
    }

    @Override
    public void print(char c) throws IOException {
        this.original.print(c);
    }

    @Override
    public void print(int i) throws IOException {
        this.original.print(i);
    }

    @Override
    public void print(long l) throws IOException {
        this.original.print(l);
    }

    @Override
    public void print(float f) throws IOException {
        this.original.print(f);
    }

    @Override
    public void print(double d) throws IOException {
        this.original.print(d);
    }

    @Override
    public void println() throws IOException {
        this.original.println();
    }

    @Override
    public void println(String s) throws IOException {
        this.original.println(s);
    }

    @Override
    public void println(boolean b) throws IOException {
        this.original.println(b);
    }

    @Override
    public void println(char c) throws IOException {
        this.original.println(c);
    }

    @Override
    public void println(int i) throws IOException {
        this.original.println(i);
    }

    @Override
    public void println(long l) throws IOException {
        this.original.println(l);
    }

    @Override
    public void println(float f) throws IOException {
        this.original.println(f);
    }

    @Override
    public void println(double d) throws IOException {
        this.original.println(d);
    }

    @Override
    public boolean isReady() {
        return this.original.isReady();
    }

    @Override
    public void setWriteListener(WriteListener writeListener) {
        this.original.setWriteListener(writeListener);
    }

    /**
     * Close should probably close the original outputstream, as it serves as a proxy/substitute.
     * It should probably not close the other outputstream, as that might render it useless.
     */
    @Override
    public void close() throws IOException {
        this.original.close();
    }
}
