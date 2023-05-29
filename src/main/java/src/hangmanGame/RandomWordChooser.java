package src.hangmanGame;

import java.util.List;

public class RandomWordChooser {

    private static final List<HangmanWord> WORDS;
    private static final int WORDS_SIZE;

    static {
        WORDS = List.of(HangmanWord.values());
        WORDS_SIZE = WORDS.size();
    }

    // Suppresses default constructor, ensuring non-instantiability.
    private RandomWordChooser() { }

    public static HangmanWord chooseWord() {
        int randomIndex = (int) (Math.random() * WORDS_SIZE);
        return WORDS.get(randomIndex);
    }
}
