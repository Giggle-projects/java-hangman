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
}
