package com.github.jvanheesch.builder.compiletime;

public interface BuilderOption2<B extends BuilderOption2> {
    <R extends B> R option2();
}
