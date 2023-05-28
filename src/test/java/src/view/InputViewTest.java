package src.view;

import org.junit.jupiter.api.*;
import src.hangmanGame.Hangman;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

class InputViewTest {

    @Test
    void inputHangmanInfo_생성_테스트() {
        String input = "1, 4  ";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        Hangman hangman = InputView.inputHangmanInfo();
        Assertions.assertTrue(hangman.equals(new Hangman("1", "4")));
    }

    @Test
    void inputHangmanInfo_콤마로_구분되는_값이_2개가_아닌_경우() {
        String input = "1, 4, 3";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Hangman hangman = InputView.inputHangmanInfo();
        });
    }

    @Test
    void inputHangmanInfo_콤마로_구분되는_값이_정수값이_아닌_경우() {
        String input = "1, test";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Hangman hangman = InputView.inputHangmanInfo();
        });
    }

    @Test
    void inputAlphabetTest() {
        String input = "das";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            InputView.inputAlphabet("");
        });
    }
}
