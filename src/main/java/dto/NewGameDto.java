package dto;

public class NewGameDto {

    private final int gameRound;
    private final int wordLength;

    public NewGameDto(int gameRound, int wordLength) {
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
