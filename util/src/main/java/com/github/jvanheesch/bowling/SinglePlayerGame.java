package com.github.jvanheesch.bowling;

import java.util.ArrayList;
import java.util.List;

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
                    if (iFrame.getRolls().size() == 1) {
                        // strike
                        try {
                            IFrame nextFrame = this.frames.get(i + 1);
                            sum += nextFrame.getRolls().get(0);
                            if (nextFrame.getRolls().size() > 1) {
                                sum += nextFrame.getRolls().get(1);
                            } else {
                                IFrame nextNextFrame = this.frames.get(i + 2);
                                sum += nextNextFrame.getRolls().get(0);
                            }
                        } catch (IndexOutOfBoundsException e) {
                            //
                        }
                    } else if (iFrame.getRolls().size() == 2) {
                        // spare
                        // TODO_JORIS: ONLY IF EXISTS !!
                        try {
                            IFrame nextFrame = this.frames.get(+1);
                            sum += nextFrame.getRolls().get(0);
                        } catch (IndexOutOfBoundsException e) {
                            //
                        }
                    } else {
                        throw new IllegalStateException();
                    }

                }
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        perfectGame();
        nearPerfectGame();
    }

    private static void perfectGame() {
        SinglePlayerGame game = new SinglePlayerGame();
        game.roll(10);
        game.roll(10);
        game.roll(10);
        game.roll(10);
        game.roll(10);
        game.roll(10);
        game.roll(10);
        game.roll(10);
        game.roll(10);
        game.roll(10);
        game.roll(10);
        game.roll(10);
        System.out.println(game.score());
    }

    private static void nearPerfectGame() {
        SinglePlayerGame game = new SinglePlayerGame();
        game.roll(10);
        game.roll(10);
        game.roll(10);
        game.roll(10);
        game.roll(10);
        game.roll(10);
        game.roll(10);
        game.roll(10);
        game.roll(10);
        game.roll(9);
        System.out.println(game.score());
    }
}
