package domain.hangman;

public class Round {

    private final int round;
    private int currentRound;

    public Round(int round) {
        this.validate(round);
        this.round = round;
        this.currentRound = 1;
    }

    private void validate(int round) {
        if (round < 1) {
            throw new IllegalArgumentException("게임 횟수는 1 이상이어야 합니다.");
        }
    }

    @Override
    public String toString() {
        return "Round{" +
                "round=" + round +
                ", currentRound=" + currentRound +
                '}';
    }

    public int getCurrentRound() {
        return currentRound;
    }
}
