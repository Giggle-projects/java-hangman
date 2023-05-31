package src;

import src.hangmanGame.Hangman;
import src.hangmanGame.HangmanGameRoundTable;
import src.hangmanGame.HangmanGameTable;
import src.util.Console;
import src.view.InputView;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class HangManApplication {

    public static void main(String[] args) {
        Hangman hangman = generateHangman();
        hangman.gameSet();
        Console.close();
    }

    public static Hangman generateHangman() {
        Hangman.HangmanInfo hangmanInfo = InputView.inputHangmanInfo();
        Map<Integer, HangmanGameRoundTable> hangmanGameRoundTableMap = new HashMap<>();
        for (int i = 0; i < hangmanInfo.numberGames; i++) {
            hangmanGameRoundTableMap.put(i+1, new HangmanGameRoundTable(new TreeMap<>()));
        }
        return new Hangman(hangmanInfo, new HangmanGameTable(hangmanGameRoundTableMap));
    }
}
