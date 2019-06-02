package com.github.jvanheesch.bowling;

import java.util.List;

public interface IFrame {
    List<Integer> getRolls();

    boolean isCompleted();

    void roll(int numberOfPins);

    default int getTotalNumberOfPinsKnockedDown() {
        return this.getRolls().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    default int getNumberOfPinsCurrentlyStanding() {
        int nbOfPinsCurrentlyKnockedDown = this.getTotalNumberOfPinsKnockedDown() % 10;

        return 10 - nbOfPinsCurrentlyKnockedDown;
    }
}
