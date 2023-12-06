package dto;

public class newGameDto {

    private final int gameRound;
    private final int wordLength;

    public newGameDto(int gameRound, int wordLength) {
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
