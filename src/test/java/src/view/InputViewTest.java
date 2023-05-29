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
        Assertions.assertTrue(hangman.equals(new Hangman(1, 4)));
    }

    @Test
    void inputAlphabet_정상_반환_테스트() {
        String input = "a";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        char singleAlphabet = InputView.inputAlphabet("inputAlphabet 정산 반환 테스트");
        Assertions.assertEquals('a', singleAlphabet);
    }
}
