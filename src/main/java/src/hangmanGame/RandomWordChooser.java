package src.hangmanGame;

import java.util.List;

public class RandomWordChooser {

    private static final List<HangmanWord> WORDS = List.of(HangmanWord.values());

    public static HangmanWord chooseWord() {
        int randomIndex = (int) (Math.random() * WORDS.size());
        return WORDS.get(randomIndex);
    }
}
