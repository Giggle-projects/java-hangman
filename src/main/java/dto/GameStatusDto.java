package dto;

import domain.hangman.HangmanGame;

public class GameStatusDto {

    private final int gameCount;
    private final int remainingLife;
    private final String word;
    private final boolean[] isMatched;

    private GameStatusDto(int gameCount, int remainingLife, String word, boolean[] isMatched) {
        this.gameCount = gameCount;
        this.remainingLife = remainingLife;
        this.word = word;
        this.isMatched = isMatched;
    }

    public static GameStatusDto from(HangmanGame hangmanGame) {
        return new GameStatusDto(
                hangmanGame.getGameCount(),
                hangmanGame.getRemainingLife(),
                hangmanGame.getWord(),
                hangmanGame.getIsMatched());
    }

    public int getGameCount() {
        return gameCount;
    }

    public int getRemainingLife() {
        return remainingLife;
    }

    public String getHint(String hiddenWord) {
        StringBuilder hint = new StringBuilder();
        String[] wordSpelling = word.split("");

        for (int i = 0; i < word.length(); i++) {
            String currentChar = hiddenWord;
            if (isMatched[i]) {
                currentChar = wordSpelling[i];
            }
            hint.append(currentChar);
        }
        return hint.toString();
    }
}
