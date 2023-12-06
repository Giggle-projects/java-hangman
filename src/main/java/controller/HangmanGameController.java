package controller;

import domain.hangman.Alphabet;
import domain.hangman.HangmanGame;
import domain.hangman.Life;
import domain.hangman.Round;
import domain.randomWordPicker.HangmanWordPicker;
import domain.randomWordPicker.RandomWordPicker;
import dto.GameStatusDto;
import dto.NewGameDto;
import view.InputView;
import view.OutputView;

public class HangmanGameController {

    public HangmanGameController() {

    }

    public void run() {
        HangmanGame hangmanGame = this.generateHangmanGame();

        while (!hangmanGame.isDone()) {
            this.startRound(hangmanGame);
            boolean result = hangmanGame.getResult();
            OutputView.printGameResult(result);
        }
    }

    private HangmanGame generateHangmanGame() {
        Round round = InputView.inputRound();
        Life life = InputView.inputLife();
        RandomWordPicker randomWordPicker = HangmanWordPicker.getInstance();

        return new HangmanGame(round, life, randomWordPicker);
    }

    private void startRound(HangmanGame hangmanGame) {
        NewGameDto newGameDto = hangmanGame.setNewRound();
        OutputView.printGameInfo(newGameDto);

        while (!hangmanGame.isRoundEnd()) {
            Alphabet alphabet = InputView.inputAlphabet();
            GameStatusDto gameStatusDto = hangmanGame.tryToMatch(alphabet);
            OutputView.printGameStatus(gameStatusDto);
        }
    }
}
