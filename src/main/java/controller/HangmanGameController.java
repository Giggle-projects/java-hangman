package controller;

import domain.*;
import view.InputView;

public class HangmanGameController {

    public HangmanGameController() {

    }

    public void run() {
        HangmanGame hangmanGame = this.generateHangmanGame();
    }

    private HangmanGame generateHangmanGame() {
        Round round = InputView.inputRound();
        Life life = InputView.inputLife();
        RandomWordPicker randomWordPicker = HangmanWordPicker.getInstance();

        return new HangmanGame(round, life, randomWordPicker);
    }
}
