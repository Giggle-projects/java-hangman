package src.view;

import org.junit.jupiter.api.*;
import src.hangmanGame.Hangman;
import src.hangmanGame.HangmanGameTable;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;

class InputViewTest {

    @Test
    void inputHangmanInfo_생성_테스트() {
        String input = "1, 4  ";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        Hangman hangman = new Hangman(InputView.inputHangmanInfo(), new HangmanGameTable(new HashMap<>()));
        Assertions.assertTrue(hangman.equals(new Hangman(new Hangman.HangmanInfo(1, 4), new HangmanGameTable(new HashMap<>()))));
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
