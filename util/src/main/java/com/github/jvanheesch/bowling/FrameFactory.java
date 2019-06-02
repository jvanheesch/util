package com.github.jvanheesch.bowling;

import java.util.ArrayList;
import java.util.List;

public class FrameFactory {
    public static IFrame createFrame(int frameNumber) {
        // TODO_JORIS: zero based? probs want to be in sync with index in list !!
        if (frameNumber < 1 || frameNumber > 10) {
            throw new IllegalArgumentException();
        }
        return frameNumber == 10
                ? new FinalFrame()
                : new RegularFrame();
    }

    private static abstract class AbstractFrame implements IFrame {
        private final List<Integer> rolls = new ArrayList<>();

        @Override
        public List<Integer> getRolls() {
            return new ArrayList<>(this.getRollsInternal());
        }

        // TODO_JORIS attrocious
        protected List<Integer> getRollsInternal() {
            return this.rolls;
        }
    }

    private static class RegularFrame extends AbstractFrame {

        @Override
        public void roll(int numberOfPins) {
            if (this.isCompleted()) {
                throw new IllegalStateException();
            }
            if (numberOfPins < 0 || numberOfPins > this.getNumberOfPinsCurrentlyStanding()) {
                throw new IllegalArgumentException();
            }

            this.getRollsInternal().add(numberOfPins);
        }

        @Override
        public boolean isCompleted() {
            return this.getRollsInternal().size() == 2 ||
                    this.getRollsInternal().size() == 1 && this.getRollsInternal().get(0) == 10;
        }

        @Override
        public int getNumberOfPinsCurrentlyStanding() {
            return 10 - this.getTotalNumberOfPinsKnockedDown();
        }
    }

    private static class FinalFrame extends AbstractFrame {
        @Override
        public void roll(int numberOfPins) {
            if (this.isCompleted()) {
                throw new IllegalStateException();
            }
            if (numberOfPins < 0) {
                throw new IllegalArgumentException();
            }
            if (numberOfPins > this.getNumberOfPinsCurrentlyStanding()) {
                throw new IllegalArgumentException();
            }
            this.getRollsInternal().add(numberOfPins);
        }

        @Override
        public boolean isCompleted() {
            return (this.getRollsInternal().size() == 2 &&
                    this.getTotalNumberOfPinsKnockedDown() < 10)
                    || this.getRollsInternal().size() == 3;
        }

        @Override
        public int getNumberOfPinsCurrentlyStanding() {
            int nbOfPinsCurrentlyKnockedDown = this.getTotalNumberOfPinsKnockedDown() % 10;

            return 10 - nbOfPinsCurrentlyKnockedDown;
        }
    }
}
