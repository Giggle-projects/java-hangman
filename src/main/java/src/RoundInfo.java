package src;

public class RoundInfo {
    private int roundId;
    private int lives;
    private String guess;
    private char input;

    public RoundInfo(int roundId, int lives, String guess, char input) {
        this.roundId = roundId;
        this.lives = lives;
        this.guess = guess;
        this.input = input;
    }

    @Override
    public String toString() {
        return "라운드 id :" + this.roundId + ", 남은 목숨 : " + this.lives + ", " + this.guess + ", 사용자 입력 :" + this.input;
    }
}
