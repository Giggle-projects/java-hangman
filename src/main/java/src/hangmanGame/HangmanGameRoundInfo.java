package src.hangmanGame;

public class HangmanGameRoundInfo {

    private static final String INFO_PRINT_FORMAT = "남은 목숨 : %d, %s, 사용자 입력 : %c";

    private final int remainingLife;
    private final String correctingWord;
    private final char inputAlphabet;

    public HangmanGameRoundInfo(int remainingLife, String correctingWord, char inputAlphabet) {
        this.remainingLife = remainingLife;
        this.correctingWord = correctingWord;
        this.inputAlphabet = inputAlphabet;
    }

    @Override
    public String toString() {
        return String.format(INFO_PRINT_FORMAT, remainingLife, correctingWord, inputAlphabet);
    }
}
