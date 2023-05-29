package src.hangmanGame;

import src.view.InputView;
import src.view.OutputView;

public class HangmanGame {

    private static final String BLANK_ALPHABET = "_";
    private static final String GAME_EPISODE_INFO_FORMAT = "%d번째 게임이 시작됩니다. 정답 단어는 %d글자 입니다.";
    private static final String GAME_ROUND_INFO_FORMAT = "%d 라운드 : %s, 목숨 %d";
    private static final String GAME_RESULT_FORMAT = "게임 id : %d, 추측 : %s, 남은 목숨 : %d, 정답 : %s\n\n";

    private static final String GAME_SUCCESSFUL_MESSAGE = "축하합니다. 정답입니다.\n";
    private static final String GAME_FAILED_MESSAGE = "아쉽습니다. 오답입니다.\n";

    private static final String GAME_SUCCESS = "성공";
    private static final String GAME_FAIL = "실패";

    private static final int FIRST_ROUND = 1;

    private final String word;
    private final int gameId;
    private final HangmanGameRoundTable roundTable;

    private int life;
    private String correctingWord;
    private String gameResult;

    HangmanGame(int numberGames, int life, HangmanWord word, HangmanGameRoundTable roundTable) {
        this.gameId = numberGames;
        this.life = life;
        this.word = word.getSpelling();
        this.roundTable = roundTable;

        correctingWord = BLANK_ALPHABET.repeat(this.word.length());
        gameResult = GAME_FAIL;
    }

    public void start() {
        OutputView.printMessage(String.format(GAME_EPISODE_INFO_FORMAT, gameId, word.length()));
        int gameRound = FIRST_ROUND;

        do {
            startRound(gameRound++);
        } while (!checkGameResult());

        saveResultToTable();
        printResult();
    }

    private void startRound(int gameRound) {
        String roundInfo = String.format(GAME_ROUND_INFO_FORMAT, gameRound, correctingWord, life);
        char alphabet = InputView.inputAlphabet(roundInfo);
        checkContainedWord(alphabet);

        saveRoundToTable(gameRound, alphabet);
    }

    private void checkContainedWord(char alphabet) {
        if (!word.contains(String.valueOf(alphabet))) {
            life--;
            return;
        }

        char[] correctingWordArray = correctingWord.toCharArray();
        char[] wordCharArray = word.toCharArray();

        for (int idx = 0; idx < word.length(); idx++) {
            if (wordCharArray[idx] == alphabet)
                correctingWordArray[idx] = alphabet;
        }

        correctingWord = new String(correctingWordArray);
    }

    private boolean checkGameResult() {
        if (!correctingWord.contains(BLANK_ALPHABET)) {
            gameResult = GAME_SUCCESS;
            OutputView.printMessage(GAME_SUCCESSFUL_MESSAGE);
            return true;
        }

        if (life == 0) {
            OutputView.printMessage(GAME_FAILED_MESSAGE);
            return true;
        }

        return false;
    }

    private void saveRoundToTable(int gameRound, char alphabet) {
        roundTable.saveRound(gameRound,
                new HangmanGameRoundTable.HangmanGameRoundInfo(life, correctingWord, alphabet));
    }

    private void saveResultToTable() {
        roundTable.saveGameResult(String.format(GAME_RESULT_FORMAT, gameId, gameResult, life, word));
    }

    private void printResult() {
        OutputView.printMessage(roundTable.gameResutToString());
    }
}
