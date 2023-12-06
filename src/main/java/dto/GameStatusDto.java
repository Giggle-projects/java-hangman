package dto;

public class GameStatusDto {

    private final int gameCount;
    private final int remainingLife;
    private final WordDto wordDto;

    public GameStatusDto(int gameCount, int remainingLife, WordDto wordDto) {
        this.gameCount = gameCount;
        this.remainingLife = remainingLife;
        this.wordDto = wordDto;
    }

    public int getGameCount() {
        return gameCount;
    }

    public int getRemainingLife() {
        return remainingLife;
    }

    public WordDto getWordDto() {
        return wordDto;
    }
}
