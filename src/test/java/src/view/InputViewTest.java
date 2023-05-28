package src.view;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import src.hangmanGame.HangmanInfo;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

class InputViewTest {

    @Test
    void inputHangmanGameInfo_생성_테스트() {
        String input = "1, 4  ";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        HangmanInfo hangmanGameInfo = InputView.inputHangmanGameInfo();
        Assertions.assertTrue(hangmanGameInfo.equals(new HangmanInfo("1", "4")));
    }

    @Test
    void inputHangmanGameInfo_콤마로_구분되는_값이_2개가_아닌_경우() {
        String input = "1, 4, 3";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            HangmanInfo hangmanGameInfo = InputView.inputHangmanGameInfo();
        });
    }

    @Test
    void inputHangmanGameInfo_콤마로_구분되는_값이_정수값이_아닌_경우() {
        String input = "1, test";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            HangmanInfo hangmanGameInfo = InputView.inputHangmanGameInfo();
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
