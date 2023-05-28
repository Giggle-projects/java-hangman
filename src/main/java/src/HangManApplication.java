package src;

import src.hangmanGame.Hangman;
import src.util.Console;
import src.view.InputView;

public class HangManApplication {

    public static void main(String[] args) {
        Hangman hangman = InputView.inputHangmanInfo();
        hangman.gameSet();
        Console.close();
    }
}
