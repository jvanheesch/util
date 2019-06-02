package com.github.jvanheesch.builder.compiletime;

public interface BaseBuilder<B extends BaseBuilder<B>> {
    B doStuff();
}
