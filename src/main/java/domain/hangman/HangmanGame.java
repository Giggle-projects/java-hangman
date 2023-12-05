package domain.hangman;

import domain.randomWordPicker.RandomWordPicker;
import dto.GameInitDto;

public class HangmanGame {

    private final Round round;
    private final Life life;
    private final RandomWordPicker randomWordPicker;

    private Word word;

    public HangmanGame(Round round, Life life, RandomWordPicker randomWordPicker) {
        this.round = round;
        this.life = life;
        this.randomWordPicker = randomWordPicker;
        word = randomWordPicker.pick();
    }

    public GameInitDto getInitDto() {
        int round = this.round.getCurrentRound();
        int wordLength = word.length();

        return new GameInitDto(round, wordLength);
    }
}
