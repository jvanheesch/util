package com.github.jvanheesch.io;

import com.github.jvanheesch.Executable;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.io.IoBuilder;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public final class IOStreams {
    private IOStreams() {
    }

    public static InputStream logInput(InputStream inputStream, Logger logger) {
        return new InterceptorInputStream(inputStream, IoBuilder.forLogger(logger).buildOutputStream());
    }

    public static ServletInputStream logInput(ServletInputStream inputStream, Logger logger) {
        return new InterceptorServletInputStream(inputStream, IoBuilder.forLogger(logger).buildOutputStream());
    }

    public static OutputStream logOutput(OutputStream outputStream, Logger logger) {
        return new InterceptorOutputStream(outputStream, IoBuilder.forLogger(logger).buildOutputStream());
    }

    public static ServletOutputStream logOutput(ServletOutputStream outputStream, Logger logger) {
        return new InterceptorServletOutputStream(outputStream, IoBuilder.forLogger(logger).buildOutputStream());
    }

    public static InputStream closeNoOpOutputStreamWrapper(InputStream inputStream) {
        return replaceCloseAction(inputStream, Executable.noop());
    }

    public static InputStream replaceCloseAction(InputStream inputStream, Executable onClose) {
        return new ForwardingInputStream(inputStream) {
            @Override
            public void close() {
                onClose.executeSilently();
            }
        };
    }

    public static InputStream onBeforeClose(InputStream inputStream, Executable onBeforeClose) {
        return replaceCloseAction(inputStream, () -> {
            onBeforeClose.executeSilently();
            inputStream.close();
        });
    }

    public static InputStream onAfterClose(InputStream inputStream, Executable onAfterClose) {
        return replaceCloseAction(inputStream, () -> {
            onAfterClose.executeSilently();
            inputStream.close();
        });
    }

    public static OutputStream closeNoOpOutputStreamWrapper(OutputStream outputStream) {
        return replaceCloseAction(outputStream, Executable.noop());
    }

    public static OutputStream replaceCloseAction(OutputStream outputStream, Executable onClose) {
        return new ForwardingOutputStream(outputStream) {
            @Override
            public void close() {
                onClose.executeSilently();
            }
        };
    }

    public static OutputStream onBeforeClose(OutputStream outputStream, Executable onBeforeClose) {
        return replaceCloseAction(outputStream, () -> {
            onBeforeClose.executeSilently();
            outputStream.close();
        });
    }

    public static OutputStream onAfterClose(OutputStream outputStream, Executable onAfterClose) {
        return replaceCloseAction(outputStream, () -> {
            onAfterClose.executeSilently();
            outputStream.close();
        });
    }
}
