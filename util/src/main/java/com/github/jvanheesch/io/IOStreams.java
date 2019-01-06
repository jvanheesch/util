package com.github.jvanheesch.io;

import com.github.jvanheesch.Executable;

import java.io.InputStream;
import java.io.OutputStream;

public final class IOStreams {
    private IOStreams() {
    }

    public static InputStream closeNoOpOutputStreamWrapper(InputStream inputStream) {
        return replaceCloseAction(inputStream, Executable.noop());
    }

    public static InputStream replaceCloseAction(InputStream inputStream, Executable onClose) {
        return new SingleReadForwardingInputStream(inputStream) {
            @Override
            public void close() {
                Executable.executeSilently(onClose);
            }
        };
    }

    public static InputStream onBeforeClose(InputStream inputStream, Executable onBeforeClose) {
        return replaceCloseAction(inputStream, () -> {
            Executable.executeSilently(onBeforeClose);
            inputStream.close();
        });
    }

    public static InputStream onAfterClose(InputStream inputStream, Executable onAfterClose) {
        return replaceCloseAction(inputStream, () -> {
            Executable.executeSilently(onAfterClose);
            inputStream.close();
        });
    }

    public static OutputStream closeNoOpOutputStreamWrapper(OutputStream outputStream) {
        return replaceCloseAction(outputStream, Executable.noop());
    }

    public static OutputStream replaceCloseAction(OutputStream outputStream, Executable onClose) {
        return new SingleWriteForwardingOutputStream(outputStream) {
            @Override
            public void close() {
                Executable.executeSilently(onClose);
            }
        };
    }

    public static OutputStream onBeforeClose(OutputStream outputStream, Executable onBeforeClose) {
        return replaceCloseAction(outputStream, () -> {
            Executable.executeSilently(onBeforeClose);
            outputStream.close();
        });
    }

    public static OutputStream onAfterClose(OutputStream outputStream, Executable onAfterClose) {
        return replaceCloseAction(outputStream, () -> {
            Executable.executeSilently(onAfterClose);
            outputStream.close();
        });
    }
}