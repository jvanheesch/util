package com.github.jvanheesch.builder.vehicle;

public class CarBuilderExample {
    public static void main(String[] args) {
        new CarBuilder()
                .red()
                // .red() does not compile!
                .bmw()
                .sports();
    }
}
