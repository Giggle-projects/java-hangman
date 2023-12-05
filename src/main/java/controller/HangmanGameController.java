package controller;

import domain.hangman.HangmanGame;
import domain.hangman.Life;
import domain.hangman.Round;
import domain.randomWordPicker.HangmanWordPicker;
import domain.randomWordPicker.RandomWordPicker;
import dto.GameInitDto;
import view.InputView;
import view.OutputView;

public class HangmanGameController {

    public HangmanGameController() {

    }

    public void run() {
        HangmanGame hangmanGame = this.generateHangmanGame();

        GameInitDto initDto = hangmanGame.getInitDto();
        OutputView.printGameInfo(initDto);
    }

    private HangmanGame generateHangmanGame() {
        Round round = InputView.inputRound();
        Life life = InputView.inputLife();
        RandomWordPicker randomWordPicker = HangmanWordPicker.getInstance();

        return new HangmanGame(round, life, randomWordPicker);
    }
}
