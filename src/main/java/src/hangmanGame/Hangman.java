package src.hangmanGame;

import src.view.OutputView;

import java.util.Objects;

public class Hangman {

    private static final String GAME_END_MESSAGE = "게임 종료. 이용해주셔서 감사합니다.";
    private static final String GAME_NEXT_MESSAGE = "다음 게임을 시작합니다.";

    private static final int MAX_NUMBER_GAMES = 100;
    private static final int MIN_NUMBER_GAMES = 1;

    private static final int MAX_LIFE = 1000;
    private static final int MIN_LIFE = 1;

    private final int numberGames;
    private final int life;

    public Hangman(int numberGames, int life) {
        validateNumberGames(numberGames);
        validateLife(life);
        this.numberGames = numberGames;
        this.life = life;
    }

    public void gameSet() {
        HangmanGame hangmanGame;

        for (int gameCount = 0; gameCount < numberGames; gameCount++) {
            hangmanGame = initHangmanGame(gameCount);
            hangmanGame.start();
            if (!isLastGame(gameCount)) {
                OutputView.printMessage(GAME_NEXT_MESSAGE);
            }
        }
        OutputView.printMessage(GAME_END_MESSAGE);
    }

    private HangmanGame initHangmanGame(int gameCount) {
        int numberGames = gameCount + 1;
        int life = this.life;
        HangmanWord randomWord = RandomWordChooser.chooseWord();
        HangmanGameRoundTable roundTable = new HangmanGameRoundTable();

        return new HangmanGame(numberGames, life, randomWord, roundTable);
    }

    private boolean isLastGame(int gameCount ) {
        return gameCount == numberGames - 1;
    }


    private void validateNumberGames(int numberGames) throws IllegalArgumentException {
        if (MIN_NUMBER_GAMES > numberGames || MAX_NUMBER_GAMES < numberGames) {
            throw new IllegalArgumentException("게임 횟수 범위 밖의 숫자입니다.");
        }
    }

    private void validateLife(int life) throws IllegalArgumentException {
        if (MIN_LIFE > life || MAX_LIFE < life) {
            throw new IllegalArgumentException("목숨 횟수 범위 밖의 숫자입니다.");
        }
    }

    public boolean equals(Hangman that) {
        return Objects.equals(numberGames, that.numberGames)
                && Objects.equals(life, that.life);
    }
}
