package com.github.jvanheesch.bowling;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SinglePlayerGameTest {

    @Test
    void test_perfect_game() {
        SinglePlayerGame game = new SinglePlayerGame();

        IntStream.generate(() -> 10)
                .limit(12)
                .forEach(game::roll);

        assertThat(game.score())
                .isEqualTo(300);

        assertThat(game.isFinished())
                .isTrue();

        assertThatThrownBy(() -> game.roll(1))
                .isInstanceOf(IllegalStateException.class);
    }
}
