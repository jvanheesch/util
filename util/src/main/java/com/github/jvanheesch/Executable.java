package com.github.jvanheesch;

/**
 * TODO_JORIS cleanup + make consistent with Computable
 */
@FunctionalInterface
public interface Executable {
    void execute() throws Throwable;

    static Runnable supplier(Executable executable) {
        return () -> {
            try {
                executable.execute();
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        };
    }

    static void executeSilently(Executable executable) {
        try {
            executable.execute();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
