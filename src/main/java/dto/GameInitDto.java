package dto;

public class GameInitDto {

    private final int gameRound;
    private final int wordLength;

    public GameInitDto(int gameRound, int wordLength) {
        this.gameRound = gameRound;
        this.wordLength = wordLength;
    }

    public int gameRound() {
        return gameRound;
    }

    public int wordLength() {
        return wordLength;
    }
}
