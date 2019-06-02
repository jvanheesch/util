package com.github.jvanheesch.bowling;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

public class Frame {
    private final int frameNumber;
    private final List<Integer> rolls;

    public Frame(int frameNumber) {
        if (frameNumber < 0 || frameNumber > 10) {
            throw new IllegalArgumentException();
        }
        this.frameNumber = frameNumber;
        this.rolls = new ArrayList<>();
    }

    public void roll(int roll) {
        if (isCompleted()) {
            throw new IllegalStateException();
        }
        this.rolls.add(roll);
    }

    public boolean isCompleted() {
        throw new NotImplementedException();
    }
}
