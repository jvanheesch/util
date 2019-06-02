package com.github.jvanheesch.bowling;

import java.util.ArrayList;
import java.util.List;
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
        return this.frames.stream()
                .mapToInt(frame -> {
                            int score = frame.getTotalNumberOfPinsKnockedDown();

                            if (score == 10) {
                                score += Stream.iterate(getNextFrame(frame), optionalNextFrame -> optionalNextFrame.flatMap(this::getNextFrame))
                                        .takeWhile(Optional::isPresent)
                                        .flatMap(Optional::stream)
                                        .map(IFrame::getRolls)
                                        .flatMap(List::stream)
                                        .mapToInt(Integer::intValue)
                                        // TODO_JORIS: less 'robust' / expressive: not clear that only 1 & 2 are valid options.
                                        .limit(frame.getRolls().size() == 1 ? 2 : 1)
                                        .sum();
                            }
                            return score;
                        }
                )
                .sum();
    }

    private Optional<IFrame> getNextFrame(IFrame frame) {
        int index = this.frames.indexOf(frame);

        return index != -1 && index < this.frames.size() - 1
                ? Optional.of(this.frames.get(index + 1))
                : Optional.empty();
    }
}
