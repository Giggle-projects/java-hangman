package domain.hangman;

import domain.randomWordPicker.RandomWordPicker;
import dto.GameStatusDto;
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
        return life.isDone() || word.isAllMatched();
    }

    public newGameDto setNewRound() {
        round.increase();
        life.recover();
        word = randomWordPicker.pick();
        gameCount = 0;

        return new newGameDto(round.getCurrentRound(), word.length());
    }

    public GameStatusDto tryToMatch(Alphabet alphabet) {
        gameCount += 1;
        boolean result = word.tryToMatch(alphabet);
        if (!result) {
            life.decrease();
        }
        return new GameStatusDto(gameCount, life.getRemainingLife(), word.toDto());
    }
}
