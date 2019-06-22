package com.github.jvanheesch.builder.vehicle.improved;

import com.github.jvanheesch.builder.compiletime.BaseBuilder;

public class ImprovedCarBuilder<
        BEIGE_AND_RED extends Red<AUDI_AND_BMW> & Beige<AUDI_AND_BMW> & BaseBuilder<AUDI_AND_BMW>,
        BEIGE_AND_RED_TERMINAL extends Red<Void> & Beige<Void> & BaseBuilder<AUDI_AND_BMW>,
        AUDI_AND_BMW extends Audi<BEIGE_AND_RED> & Bmw<BEIGE_AND_RED> & BaseBuilder<AUDI_AND_BMW>,
        AUDI_AND_BMW_TERMINAL extends Audi<Void> & Bmw<Void> & BaseBuilder<AUDI_AND_BMW>>
        implements
        Audi<BEIGE_AND_RED_TERMINAL>,
        Bmw<BEIGE_AND_RED_TERMINAL>,
        Red<AUDI_AND_BMW_TERMINAL>,
        Beige<AUDI_AND_BMW_TERMINAL>,
        BaseBuilder<ImprovedCarBuilder<BEIGE_AND_RED, BEIGE_AND_RED_TERMINAL, AUDI_AND_BMW, AUDI_AND_BMW_TERMINAL>> {

    // return (R) this;
    // won't compile, because now 'this' has two red methods:
    // 1 returning AUDI_AND_BMW, the other returning void
    @Override
    public <R extends BEIGE_AND_RED_TERMINAL> R audi() {
        System.out.println("SomeBuilder.audi");
        return null;
    }

    @Override
    public <R extends BEIGE_AND_RED_TERMINAL> R bmw() {
        System.out.println("SomeBuilder.bmw");
        return null;
    }

    @Override
    public <R extends AUDI_AND_BMW_TERMINAL> R red() {
        System.out.println("SomeBuilder.red");
        return null;
    }

    @Override
    public <R extends AUDI_AND_BMW_TERMINAL> R beige() {
        System.out.println("SomeBuilder.beige");
        return null;
    }

    @Override
    public ImprovedCarBuilder<BEIGE_AND_RED, BEIGE_AND_RED_TERMINAL, AUDI_AND_BMW, AUDI_AND_BMW_TERMINAL> doStuff() {
        return this;
    }

    public static void main(String[] args) {
        new ImprovedCarBuilder<>()
                .doStuff()
                .audi()
                .red();
        new ImprovedCarBuilder<>()
                .red()
                .bmw();
    }
}
