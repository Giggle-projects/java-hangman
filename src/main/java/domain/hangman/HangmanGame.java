package domain.hangman;

import domain.randomWordPicker.RandomWordPicker;
import dto.newGameDto;

public class HangmanGame {

    private final Round round;
    private final Life life;
    private final RandomWordPicker randomWordPicker;

    private Word word;
    private int gameCount;

    public HangmanGame(Round round, Life life, RandomWordPicker randomWordPicker) {
        this.round = round;
        this.life = life;
        this.randomWordPicker = randomWordPicker;
    }

    public boolean isDone() {
        return life.isDone();
    }

    public newGameDto setNewRound() {
        round.increase();
        life.recover();
        word = randomWordPicker.pick();
        gameCount = 1;

        return new newGameDto(round.getCurrentRound(), word.length());
    }
}
