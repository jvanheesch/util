package com.github.jvanheesch.builder.vehicle.improved;

import com.github.jvanheesch.builder.compiletime.BaseBuilder;
import com.github.jvanheesch.builder.compiletime.BuilderOption1;
import com.github.jvanheesch.builder.compiletime.BuilderOption2;

public class ImprovedCarBuilder<
        BEIGE_AND_RED extends Red<AUDI_AND_BMW> & Beige<AUDI_AND_BMW> & BaseBuilder<AUDI_AND_BMW>,
        BEIGE_AND_RED_TERMINAL extends Red<Void> & Beige<Void> & BaseBuilder<AUDI_AND_BMW>,
        AUDI_AND_BMW extends Audi<BEIGE_AND_RED> & Bmw<BEIGE_AND_RED> & BaseBuilder<AUDI_AND_BMW>,
        AUDI_AND_BMW_TERMINAL extends Audi<Void> & Bmw<Void> & BaseBuilder<AUDI_AND_BMW>>
        implements
        Audi<BEIGE_AND_RED_TERMINAL>,
        Bmw<BEIGE_AND_RED_TERMINAL>,
        BaseBuilder<ImprovedCarBuilder<BEIGE_AND_RED, BEIGE_AND_RED_TERMINAL, AUDI_AND_BMW, AUDI_AND_BMW_TERMINAL>> {

    @Override
    public <R extends BEIGE_AND_RED_TERMINAL> R audi() {
        System.out.println("SomeBuilder.audi");
        return (R) this;
    }

    @Override
    public <R extends BEIGE_AND_RED_TERMINAL> R bmw() {
        System.out.println("SomeBuilder.bmw");
        return (R) this;
    }

    @Override
    public ImprovedCarBuilder<BEIGE_AND_RED, BEIGE_AND_RED_TERMINAL, AUDI_AND_BMW, AUDI_AND_BMW_TERMINAL> doStuff() {
        return this;
    }

    public static void main(String[] args) {
        ImprovedCarBuilder<?, ?, ?, ?> builder = new ImprovedCarBuilder<>();

        BuilderOption1<?> o1 = builder.audi();
        // o1.option1().option2();

        BuilderOption2<?> o2 = builder.bmw();
        // o2.bmw().option1();
        new ImprovedCarBuilder<>()
                .doStuff()
                .audi()
                //. .audi() does not compile
                .red()
                // .red() does not compile
//                .audi() // -> we want this NOT to compile !!
//                .red()
//                .doStuff()
//                // .option2() // does not compile
//                .audi()
        ;
    }
}