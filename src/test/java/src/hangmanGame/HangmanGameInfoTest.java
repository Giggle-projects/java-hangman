package src.hangmanGame;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HangmanGameInfoTest {

    @Test
    void validateNumberGames_예외_테스트() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new HangmanInfo("wrong value", "2");
        });
    }

    @Test
    void validateNumberGames_예외_메시지_테스트() {
        try {
            new HangmanInfo("wrong value", "2");
        } catch (IllegalArgumentException exception) {
            Assertions.assertEquals("올바른 게임 횟수를 입력해 주세요.", exception.getMessage());
        }
    }

    @Test
    void validateLife_예외_테스트() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new HangmanInfo("2", "wrong value");
        });
    }

    @Test
    void validateLife_예외_메시지_테스트() {
        try {
            new HangmanInfo("2", "wrong value");
        } catch (IllegalArgumentException exception) {
            Assertions.assertEquals("올바른 목숨을 입력해 주세요.", exception.getMessage());
        }
    }
}
