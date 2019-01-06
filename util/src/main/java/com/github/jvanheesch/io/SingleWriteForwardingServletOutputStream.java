package com.github.jvanheesch.io;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import java.io.IOException;

/**
 * In principle, it might make more sense to make this class singlePrint (just like singleWrite),
 * but I currently don't care about print.
 */
public class SingleWriteForwardingServletOutputStream extends ServletOutputStream {
    private final ServletOutputStream underlyingServletOutputStream;

    public SingleWriteForwardingServletOutputStream(ServletOutputStream underlyingServletOutputStream) {
        this.underlyingServletOutputStream = underlyingServletOutputStream;
    }

    @Override
    public void print(String s) throws IOException {
        this.underlyingServletOutputStream.print(s);
    }

    @Override
    public void print(boolean b) throws IOException {
        this.underlyingServletOutputStream.print(b);
    }

    @Override
    public void print(char c) throws IOException {
        this.underlyingServletOutputStream.print(c);
    }

    @Override
    public void print(int i) throws IOException {
        this.underlyingServletOutputStream.print(i);
    }

    @Override
    public void print(long l) throws IOException {
        this.underlyingServletOutputStream.print(l);
    }

    @Override
    public void print(float f) throws IOException {
        this.underlyingServletOutputStream.print(f);
    }

    @Override
    public void print(double d) throws IOException {
        this.underlyingServletOutputStream.print(d);
    }

    @Override
    public void println() throws IOException {
        this.underlyingServletOutputStream.println();
    }

    @Override
    public void println(String s) throws IOException {
        this.underlyingServletOutputStream.println(s);
    }

    @Override
    public void println(boolean b) throws IOException {
        this.underlyingServletOutputStream.println(b);
    }

    @Override
    public void println(char c) throws IOException {
        this.underlyingServletOutputStream.println(c);
    }

    @Override
    public void println(int i) throws IOException {
        this.underlyingServletOutputStream.println(i);
    }

    @Override
    public void println(long l) throws IOException {
        this.underlyingServletOutputStream.println(l);
    }

    @Override
    public void println(float f) throws IOException {
        this.underlyingServletOutputStream.println(f);
    }

    @Override
    public void println(double d) throws IOException {
        this.underlyingServletOutputStream.println(d);
    }

    @Override
    public boolean isReady() {
        return this.underlyingServletOutputStream.isReady();
    }

    @Override
    public void setWriteListener(WriteListener writeListener) {
        this.underlyingServletOutputStream.setWriteListener(writeListener);
    }

    @Override
    public final void write(byte[] b) throws IOException {
        super.write(b);
    }

    @Override
    public final void write(byte[] b, int off, int len) throws IOException {
        super.write(b, off, len);
    }

    @Override
    public void write(int b) throws IOException {
        this.underlyingServletOutputStream.write(b);
    }

    @Override
    public void flush() throws IOException {
        this.underlyingServletOutputStream.flush();
    }

    @Override
    public void close() throws IOException {
        this.underlyingServletOutputStream.close();
    }
}
