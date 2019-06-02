package com.github.jvanheesch.builder.compiletime;

public interface BuilderOption1<B extends BuilderOption1> {
    <R extends B> R option1();
}
