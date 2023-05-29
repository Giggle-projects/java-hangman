package src.hangmanGame;

import src.view.InputView;
import src.view.OutputView;

import java.util.Objects;

public class Hangman {

    private static final String GAME_ROUND_RESULT_FORMAT = "라운드 id : %d, %s";
    private static final String GAME_END_MESSAGE = "게임 종료. 이용해주셔서 감사합니다.";
    private static final String GAME_NEXT_MESSAGE = "다음 게임을 시작합니다.";

    private static final int MAX_NUMBER_GAMES = 100;
    private static final int MIN_NUMBER_GAMES = 1;

    private static final int MAX_LIFE = 1000;
    private static final int MIN_LIFE = 1;

    private final HangmanInfo hangmanInfo;
    private final HangmanGameTable gameTable;

    public Hangman(HangmanInfo hangmanInfo, HangmanGameTable gameTable) {
        this.hangmanInfo = hangmanInfo;
        this.gameTable = gameTable;
    }

    public void gameSet() {
        HangmanGame hangmanGame;
        for (int gameCount = 0; gameCount < hangmanInfo.numberGames; gameCount++) {
            hangmanGame = generateHangmanGame(gameCount);

            chooseMenu();

            hangmanGame.start();
            if (!isLastGame(gameCount)) {
                OutputView.printMessage(GAME_NEXT_MESSAGE);
            }
        }
        OutputView.printMessage(GAME_END_MESSAGE);
    }

    private void chooseMenu() {
        int menuNumber = InputView.inputMenuNumber();
        switch (menuNumber) {
            case 1:
                break;
            case 2:
                viewGameResult();
                chooseMenu();
            case 3:
                viewGameResultOfRound();
                chooseMenu();
        }
    }

    private void viewGameResultOfRound() {
        try {
            int gameId = InputView.inputNumberOf("게임 id를 입력해주세요.");
            HangmanGameRoundTable roundTable = gameTable.getRoundTableWithException(gameId);

            int roundId = InputView.inputNumberOf("라운드 id를 입력해주세요.");
            OutputView.printMessage(String.format(GAME_ROUND_RESULT_FORMAT, roundId, roundTable.getRound(roundId).toString()));
        } catch (IllegalArgumentException exception) {
            OutputView.printMessage(exception.getMessage());
        }
    }

    private void viewGameResult() {
        try {
            int gameId = InputView.inputNumberOf("게임 id를 입력해주세요.");
            HangmanGameRoundTable roundTable = gameTable.getRoundTableWithException(gameId);
            OutputView.printMessage(roundTable.gameResutToString());
        } catch (IllegalArgumentException exception) {
            OutputView.printMessage(exception.getMessage());
        }
    }

    private HangmanGame generateHangmanGame(int gameCount) {
        int numberGames = gameCount + 1;
        int life = hangmanInfo.life;
        HangmanWord randomWord = RandomWordChooser.chooseWord();
        HangmanGameRoundTable roundTable = gameTable.getRoundTable(numberGames);

        return new HangmanGame(numberGames, life, randomWord, roundTable);
    }

    private boolean isLastGame(int gameCount) {
        return gameCount == hangmanInfo.numberGames - 1;
    }

    public boolean equals(Hangman that) {
        return this.hangmanInfo.equals(that.hangmanInfo)
                && Objects.equals(this.gameTable, that.gameTable);
    }

    public static class HangmanInfo {
        public final int numberGames;
        public final int life;

        public HangmanInfo(int numberGames, int life) {
            validateNumberGames(numberGames);
            validateLife(life);
            this.numberGames = numberGames;
            this.life = life;
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

        public boolean equals(HangmanInfo that) {
            return Objects.equals(numberGames, that.numberGames)
                    && Objects.equals(life, that.life);
        }
    }
}
