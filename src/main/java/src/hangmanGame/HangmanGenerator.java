package src.hangmanGame;

import src.view.InputView;

public class HangmanGenerator {

    public static Hangman generate() {
        HangmanInfo info = InputView.inputHangmanGameInfo();
        return new Hangman(info);
    }
}
