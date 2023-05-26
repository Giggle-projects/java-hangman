package src.hangmanGame;

import java.util.Objects;

public class HangmanGameInfo {

    private final int numberGames;
    private final int life;

    public HangmanGameInfo(String numberGames, String life) throws IllegalArgumentException {
        this.numberGames = validateNumberGames(numberGames);
        this.life = validateLife(life);
    }

    private int validateNumberGames(String numberGames) throws IllegalArgumentException {
        try {
            return Integer.parseInt(numberGames);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("올바른 게임 횟수를 입력해 주세요.");
        }
    }

    private int validateLife(String life) throws IllegalArgumentException {
        try {
            return Integer.parseInt(life);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("올바른 목숨을 입력해 주세요.");
        }
    }

    public int getLife() {
        return life;
    }

    public int getNumberGames() {
        return numberGames;
    }

    public boolean equals(HangmanGameInfo that) {
        return Objects.equals(this.numberGames, that.numberGames) && Objects.equals(this.life, that.life);
    }
}
