package domain;

public class HangmanGame {

    private final Round round;
    private final Life life;
    private final RandomWordPicker randomWordPicker;

    private String word;

    public HangmanGame(Round round, Life life, RandomWordPicker randomWordPicker) {
        this.round = round;
        this.life = life;
        this.randomWordPicker = randomWordPicker;
    }
}
