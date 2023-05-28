package src.hangmanGame;

import src.view.OutputView;

import java.util.Objects;

public class Hangman {

    private static final String GAME_END_MESSAGE = "게임 종료. 이용해주셔서 감사합니다.";
    private static final String GAME_NEXT_MESSAGE = "다음 게임을 시작합니다.";

    private final int numberGames;
    private final int life;

    public Hangman(String numberGames, String life) {
        this.numberGames = validateNumberGames(numberGames);
        this.life = validateLife(life);
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

        return new HangmanGame(numberGames, life, randomWord);
    }

    private boolean isLastGame(int gameCount ) {
        return gameCount == numberGames - 1;
    }


    private int validateNumberGames(String numberGames) throws IllegalArgumentException {
        try {
            return Integer.parseInt(numberGames);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("올바른 게임 횟수를 입력해 주세요.");
        }
    }

    private int validateLife(String life) throws IllegalArgumentException {
        try {
            return Integer.parseInt(life);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("올바른 목숨을 입력해 주세요.");
        }
    }

    public boolean equals(Hangman that) {
        return Objects.equals(numberGames, that.numberGames)
                && Objects.equals(life, that.life);
    }
}
