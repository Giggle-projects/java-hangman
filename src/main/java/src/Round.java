package src;

public class Round {
    private final int roundId;
    private final int userLife;
    private final String blindScreen;
    private final char userAnswer;

    public Round(int roundId, int userLife, String blindScreen, char userAnswer) {
        this.roundId = roundId;
        this.userLife = userLife;
        this.blindScreen = blindScreen;
        this.userAnswer = userAnswer;
    }

    public void printRoundResult() {
        System.out.println("라운드 id : " + roundId
            + ", 남은 목숨 : " + userLife
            + ", " + blindScreen
            + ", 사용자 입력 : " + userAnswer);
    }
}
