package src.hangmanGame;

import src.view.InputView;

public class HangmanGame {

    private static final String BLANK_ALPHABET = "_";
    private static final String ROUND_INFO_FORMAT = "%d 라운드 : %s, 목숨 %d";
    private static final int FIRST_ROUND = 1;

    private final String word;
    private final int gameNumber;

    private int life;
    private String correctingWord;
    private boolean gameResult;

    HangmanGame(int numberGames, int life, HangmanWord word) {
        this.gameNumber = numberGames;
        this.life = life;
        this.word = word.getSpelling();

        correctingWord = BLANK_ALPHABET.repeat(this.word.length());
        gameResult = false;
    }

    public void start() {
        System.out.println("ou : " + gameNumber + "번째 게임이 시작됩니다. 정답 단어는 " + word.length() + "글자 입니다.");
        int gameRound = FIRST_ROUND;

        while (true) {
            startRound(gameRound++);
            if (checkGameResult()){
                break;
            }
        }
    }

    private void startRound(int gameRound) {
        String roundInfo = String.format(ROUND_INFO_FORMAT, gameRound, correctingWord, life);
        char alphabet = InputView.inputAlphabet(roundInfo);
        checkContainedWord(alphabet);
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
            gameResult = true;
            System.out.println("ou : 축하합니다. 정답입니다. (단어: " + word + ")\n");
            return true;
        }
        if (life == 0) {
            System.out.println("ou: 아쉽습니다. 오답입니다.\n");
            return true;
        }

        return false;
    }
}
