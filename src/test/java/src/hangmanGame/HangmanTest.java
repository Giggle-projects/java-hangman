package src.hangmanGame;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

class HangmanTest {

    @Test
    void validateNumberGames_예외_테스트() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Hangman(new Hangman.HangmanInfo(101, 1), new HangmanGameTable(new HashMap<>()));
        });
    }

    @Test
    void validateNumberGames_예외_메시지_테스트() {
        try {
            new Hangman(new Hangman.HangmanInfo(101, 1), new HangmanGameTable(new HashMap<>()));
        } catch (IllegalArgumentException exception) {
            Assertions.assertEquals("게임 횟수 범위 밖의 숫자입니다.", exception.getMessage());
        }
    }

    @Test
    void validateLife_예외_테스트() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Hangman(new Hangman.HangmanInfo(2, 1001), new HangmanGameTable(new HashMap<>()));
        });
    }

    @Test
    void validateLife_예외_메시지_테스트() {
        try {
            new Hangman(new Hangman.HangmanInfo(2, 1001), new HangmanGameTable(new HashMap<>()));
        } catch (IllegalArgumentException exception) {
            Assertions.assertEquals("목숨 횟수 범위 밖의 숫자입니다.", exception.getMessage());
        }
    }
}
