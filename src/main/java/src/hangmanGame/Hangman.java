package src.hangmanGame;

import src.view.OutputView;

public class Hangman {

    private static final String GAME_END_MESSAGE = "게임 종료. 이용해주셔서 감사합니다.";
    private static final String GAME_NEXT_MESSAGE = "다음 게임을 시작합니다.";

    private final HangmanInfo info;

    Hangman(HangmanInfo info) {
        this.info = info;
    }

    public void gameSet() {
        HangmanGame hangmanGame;

        for (int gameCount = 0; gameCount < info.getNumberGames(); gameCount++) {
            hangmanGame = initHangmanGame(gameCount);
            hangmanGame.start();
            if (gameCount != info.getNumberGames() - 1) {
                OutputView.printMessage(GAME_NEXT_MESSAGE);
            }
        }
        OutputView.printMessage(GAME_END_MESSAGE);
    }

    private HangmanGame initHangmanGame(int gameCount) {
        int numberGames = gameCount + 1;
        int life = info.getLife();
        HangmanWord randomWord = RandomWordChooser.chooseWord();

        return new HangmanGame(numberGames, life, randomWord);
    }
}
