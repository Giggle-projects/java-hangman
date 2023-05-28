package src;

import src.hangmanGame.Hangman;
import src.hangmanGame.HangmanGenerator;
import src.util.Console;

public class HangManApplication {

    public static void main(String[] args) {
        Hangman hangman = HangmanGenerator.generate();
        hangman.gameSet();
        Console.close();
    }
}
