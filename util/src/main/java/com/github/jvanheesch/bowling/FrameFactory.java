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
            if (numberOfPins < 0) {
                throw new IllegalArgumentException();
            }
            if (this.isCompleted()) {
                throw new IllegalStateException();
            }
            // TODO_JORIS: command pattern & undo/rollback with immutability?
            if (this.getTotalNumberOfPinsKnockedDown() + numberOfPins > 10) {
                throw new IllegalArgumentException();
            }

            this.getRollsInternal().add(numberOfPins);
        }

        @Override
        public boolean isCompleted() {
            return this.getRollsInternal().size() == 2 ||
                    this.getRollsInternal().size() == 1 && this.getRollsInternal().get(0) == 10;
        }
    }

    private static class FinalFrame extends AbstractFrame {
        @Override
        public void roll(int numberOfPins) {
            if (numberOfPins < 0) {
                throw new IllegalArgumentException();
            }
            if (this.isCompleted()) {
                throw new IllegalStateException();
            }
            if (this.getRollsInternal().size() == 1 && this.getTotalNumberOfPinsKnockedDown() != 10) {
                if (this.getTotalNumberOfPinsKnockedDown() + numberOfPins > 10) {
                    throw new IllegalArgumentException();
                }
            } else {
                // TODO_JORIS: die check komt op teveel plaatsen voor.
                // last frame = single frame, maar met mogelijk meerdere kegel resets -> model somehow!
                if (numberOfPins > 10) {
                    throw new IllegalArgumentException();
                }
            }
            this.getRollsInternal().add(numberOfPins);
        }

        @Override
        public boolean isCompleted() {
            return (this.getRollsInternal().size() == 2 &&
                    this.getTotalNumberOfPinsKnockedDown() < 10)
                    || this.getRollsInternal().size() == 3;
        }
    }
}
