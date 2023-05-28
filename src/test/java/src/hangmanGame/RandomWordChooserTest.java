package src.hangmanGame;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class RandomWordChooserTest {

    @Test
    void chooseWord() {
        List<HangmanWord> hangmanWordList = List.of(HangmanWord.values());
        Assertions.assertTrue(hangmanWordList.contains(RandomWordChooser.chooseWord()));
    }
}