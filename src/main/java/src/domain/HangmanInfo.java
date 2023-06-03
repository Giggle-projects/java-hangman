package src.domain;

import java.util.Objects;

public class HangmanInfo {

    private static final int MAX_NUMBER_GAMES = 100;
    private static final int MIN_NUMBER_GAMES = 1;

    private static final int MAX_LIFE = 1000;
    private static final int MIN_LIFE = 1;

    private final int numberGames;
    private final int life;

    public HangmanInfo(int numberGames, int life) {
        validateNumberGames(numberGames);
        validateLife(life);
        this.numberGames = numberGames;
        this.life = life;
    }

    public int numberGames() {
        return numberGames;
    }

    public int life() {
        return life;
    }

    private void validateNumberGames(int numberGames) throws IllegalArgumentException {
        if (MIN_NUMBER_GAMES > numberGames || MAX_NUMBER_GAMES < numberGames) {
            throw new IllegalArgumentException("게임 횟수 범위 밖의 숫자입니다.");
        }
    }

    private void validateLife(int life) throws IllegalArgumentException {
        if (MIN_LIFE > life || MAX_LIFE < life) {
            throw new IllegalArgumentException("목숨 횟수 범위 밖의 숫자입니다.");
        }
    }
}
