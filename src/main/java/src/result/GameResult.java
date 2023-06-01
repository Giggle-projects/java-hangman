package src.result;

public class GameResult {
    private final int gameNum;
    private int remainingLives;
    private boolean successStatus;
    private String answer;

    private static int gameSeqNum = 1;

    private GameResult() {
        gameNum = gameSeqNum;
        remainingLives = 0;
        successStatus = true;
        answer = null;
        gameSeqNum++;
    }

    private GameResult(int remainingLives, boolean successStatus, String answer) {
        this();
        this.remainingLives = remainingLives;
        this.successStatus = successStatus;
        this.answer = answer;
    }

    public static GameResult createGameResult(int remainingLives, boolean successStatus, String answer) {
        return new GameResult(remainingLives, successStatus, answer);
    }

    public int getGameNum() {
        return gameNum;
    }

    public int getRemainingLives() {
        return remainingLives;
    }

    public boolean isSuccessStatus() {
        return successStatus;
    }

    public String getAnswer() {
        return answer;
    }

    public static int getGameSeqNum() {
        return gameSeqNum;
    }

    @Override
    public String toString() {
        return "GameResult{" +
                "gameId=" + gameNum +
                ", remainingLives=" + remainingLives +
                ", successStatus=" + successStatus +
                ", answer='" + answer + '\'' +
                '}';
    }
}
