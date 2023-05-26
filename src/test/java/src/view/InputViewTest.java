package src.view;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import src.hangmanGame.HangmanGameInfo;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

class InputViewTest {

    @Test
    void inputHangmanGameInfoTest() {
        String input = "1, 4  ";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        HangmanGameInfo hangmanGameInfo = InputView.inputHangmanGameInfo();
        Assertions.assertTrue(hangmanGameInfo.equals(new HangmanGameInfo("1", "4")));
    }
}
