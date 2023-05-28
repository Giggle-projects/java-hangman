package src.hangmanGame;

import src.view.InputView;

public class HangmanGenerator {

    // Suppresses default constructor, ensuring non-instantiability.
    private HangmanGenerator() {}

    public static Hangman generate() {
        HangmanInfo info = InputView.inputHangmanGameInfo();
        return new Hangman(info);
    }
}
