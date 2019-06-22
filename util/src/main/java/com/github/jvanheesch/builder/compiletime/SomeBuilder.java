package com.github.jvanheesch.builder.compiletime;

public class SomeBuilder<
        B1 extends BuilderOption1<B1> & BaseBuilder<B1>,
        B2 extends BuilderOption2<B2> & BaseBuilder<B2>
        > implements
        BuilderOption1<B1>,
        BuilderOption2<B2>,
        BaseBuilder<SomeBuilder<B1, B2>> {

    @Override
    public <R extends B1> R option1() {
        System.out.println("SomeBuilder.option1");
        return (R) this;
    }

    @Override
    public SomeBuilder<B1, B2> doStuff() {
        return this;
    }

    @Override
    public <R extends B2> R option2() {
        throw new UnsupportedOperationException();
    }

    public static void main(String[] args) {
        SomeBuilder<?, ?> builder = new SomeBuilder<>();

        BuilderOption1<?> o1 = builder.option1();
        // o1.option1().option2();

        BuilderOption2<?> o2 = builder.option2();
        // o2.option2().option1();
        new SomeBuilder<>()
                .doStuff()
                .option1()
                .doStuff()
                // .option2() // does not compile
                .option1()
        ;
    }
}
