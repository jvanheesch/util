package com.github.jvanheesch.bowling;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class SinglePlayerGame {
    private final List<IFrame> frames;

    public SinglePlayerGame() {
        this.frames = new ArrayList<>();
        // TODO_JORIS fugly
        this.frames.add(FrameFactory.createFrame(this.frames.size() + 1));
    }

    public boolean isFinished() {
        return this.frames.size() == 10 && this.frames.get(9).isCompleted();
    }

    /**
     * TODO_JORIS: new frame exists b4 roll I think.
     * TODO_JORIS: in een echte bowling roll je gwn naar je next game denk ik.
     */
    public void roll(int numberOfPins) {
        if (this.isFinished()) {
            throw new IllegalStateException();
        }

        IFrame lastFrame = this.frames.get(this.frames.size() - 1);
        if (lastFrame.isCompleted()) {
            throw new IllegalStateException("should not happen, so remove this code.");
        }

        lastFrame.roll(numberOfPins);
        if (lastFrame.isCompleted() && !this.isFinished()) {
            this.frames.add(FrameFactory.createFrame(this.frames.size() + 1));
        }
    }

    public int score() {
        int sum = 0;
        for (int i = 0; i <= this.frames.size() - 1; i++) {
            IFrame iFrame = this.frames.get(i);
            int pinsInIthFrame = iFrame.getTotalNumberOfPinsKnockedDown();
            sum += pinsInIthFrame;
            if (pinsInIthFrame == 10) {
                // final frame, todo: fugly
                if (i == 9) {

                } else {
                    // nooit ioob omdat next-frame eagerly constructed wordt, todo: kan dit properder?
                    IntStream nextRolls = this.frames.subList(i + 1, this.frames.size())
                            .stream()
                            .flatMap(frame -> frame.getRolls().stream())
                            .mapToInt(Integer::intValue);
                    if (iFrame.getRolls().size() == 1) {
                        nextRolls = nextRolls.limit(2);
                    } else if (iFrame.getRolls().size() == 2) {
                        nextRolls = nextRolls.limit(1);
                    } else {
                        throw new IllegalStateException();
                    }

                    sum += nextRolls.sum();
                }
            }
        }
        return sum;
    }
}
