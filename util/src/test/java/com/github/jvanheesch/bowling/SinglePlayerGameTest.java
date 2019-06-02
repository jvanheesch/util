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

    @Test
    void test_all_rolls_miss() {
        SinglePlayerGame game = new SinglePlayerGame();

        IntStream.generate(() -> 0)
                .limit(20)
                .forEach(game::roll);

        assertThat(game.score())
                .isEqualTo(0);

        assertThat(game.isFinished())
                .isTrue();

        assertThatThrownBy(() -> game.roll(1))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void test_score_first_roll_strike() {
        SinglePlayerGame game = new SinglePlayerGame();

        game.roll(10);

        assertThat(game.score())
                .isEqualTo(10);

        game.roll(5);
        assertThat(game.score())
                .isEqualTo(20);

        game.roll(3);
        assertThat(game.score())
                .isEqualTo(26);

        game.roll(8);
        assertThat(game.score())
                .isEqualTo(34);
    }

    @Test
    void test_score_first_roll_spare() {
        SinglePlayerGame game = new SinglePlayerGame();

        game.roll(9);
        game.roll(1);

        assertThat(game.score())
                .isEqualTo(10);

        game.roll(5);
        assertThat(game.score())
                .isEqualTo(20);

        game.roll(3);
        assertThat(game.score())
                .isEqualTo(23);
    }

    @Test
    void test_spare_in_final_frame() {
        SinglePlayerGame game = new SinglePlayerGame();

        IntStream.generate(() -> 0)
                .limit(18)
                .forEach(game::roll);

        assertThat(game.score())
                .isEqualTo(0);

        game.roll(8);
        game.roll(2);
        game.roll(10);

        assertThat(game.isFinished())
                .isTrue();

        assertThat(game.score())
                .isEqualTo(8 + 2 + 10);

        assertThatThrownBy(() -> game.roll(1))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void test_invalid_frame_more_than_10_pins() {
        SinglePlayerGame game = new SinglePlayerGame();

        game.roll(4);

        assertThatThrownBy(() -> game.roll(7))
                .isInstanceOf(IllegalArgumentException.class);

        game.roll(6);
        assertThat(game.score())
                .isEqualTo(10);
    }

    @Test
    void test_negative_roll() {
        SinglePlayerGame game = new SinglePlayerGame();

        game.roll(9);

        assertThatThrownBy(() -> game.roll(-1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void test_negative_roll_last_frame() {
        SinglePlayerGame game = new SinglePlayerGame();

        IntStream.generate(() -> 0)
                .limit(18)
                .forEach(game::roll);

        assertThatThrownBy(() -> game.roll(-1))
                .isInstanceOf(IllegalArgumentException.class);

        game.roll(9);

        assertThatThrownBy(() -> game.roll(-1))
                .isInstanceOf(IllegalArgumentException.class);

        game.roll(1);

        assertThatThrownBy(() -> game.roll(-1))
                .isInstanceOf(IllegalArgumentException.class);

        game.roll(5);

        assertThat(game.isFinished())
                .isTrue();
    }
}
