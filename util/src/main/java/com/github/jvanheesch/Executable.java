package com.github.jvanheesch;

/**
 * TODO_JORIS cleanup + make consistent with Computable
 */
@FunctionalInterface
public interface Executable {
    void execute() throws Throwable;

    static Runnable runnable(Executable executable) {
        return () -> {
            try {
                executable.execute();
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        };
    }

    default void executeSilently() {
        try {
            this.execute();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public static Executable noop() {
        return () -> {
        };
    }
}
