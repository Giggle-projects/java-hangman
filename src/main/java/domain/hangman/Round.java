package domain.hangman;

public class Round {

    private final int round;
    private int currentRound;

    public Round(int round) {
        this.validate(round);
        this.round = round;
    }

    private void validate(int round) {
        if (round < 1) {
            throw new IllegalArgumentException("게임 횟수는 1 이상이어야 합니다.");
        }
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public void increase() {
        if (currentRound >= round) {
            throw new IllegalArgumentException("버그쓰면 안 됩니다.");
        }
        currentRound += 1;
    }

    public boolean isDone() {
        return currentRound == round;
    }
}
