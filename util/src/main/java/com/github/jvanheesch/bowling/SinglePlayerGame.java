package com.github.jvanheesch.bowling;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

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
            IFrame ithFrame = this.frames.get(i);
            int pinsInIthFrame = ithFrame.getTotalNumberOfPinsKnockedDown();
            sum += pinsInIthFrame;
            if (pinsInIthFrame == 10) {
                // not the last frame
                // alternatively: linkedlist, and last iff not hasNext()
                // then no index is needed.
                Optional<IFrame> nextFrame = getNextFrame(ithFrame);
                if (nextFrame.isPresent()) {
                    sum += Stream.iterate(nextFrame.get(), gr -> getNextFrame(gr).orElse(null))
                            // TODO_JORIS: ugly hack to terminate, due to non-availability of takeWhile().
                            .limit(100)
                            .filter(Objects::nonNull)
                            .flatMap(frame -> frame.getRolls().stream())
                            .mapToInt(Integer::intValue)
                            // TODO_JORIS: less 'robust' / expressive: not clear that only 1 & 2 are valid options.
                            .limit(ithFrame.getRolls().size() == 1 ? 2 : 1)
                            .sum();
                }
            }
        }
        return sum;
    }

    private Optional<IFrame> getNextFrame(IFrame frame) {
        int index = this.frames.indexOf(frame);

        return index != -1 && index < this.frames.size() - 1
                ? Optional.of(this.frames.get(index + 1))
                : Optional.empty();
    }
}
