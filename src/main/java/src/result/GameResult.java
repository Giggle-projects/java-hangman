package src.result;

public class GameResult {
    private int gameId;
    private int remainingLives;
    private boolean successStatus;
    private String answer;

    private GameResult(int gameId, int remainingLives, boolean successStatus, String answer) {
        this.gameId = gameId;
        this.remainingLives = remainingLives;
        this.successStatus = successStatus;
        this.answer = answer;
    }

    public static GameResult createGameResult(int gameId, int remainingLives, boolean successStatus, String answer) {
        return new GameResult(gameId, remainingLives, successStatus, answer);
    }

    public int getGameId() {
        return gameId;
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

    @Override
    public String toString() {
        return "GameResult{" +
                "gameId=" + gameId +
                ", remainingLives=" + remainingLives +
                ", successStatus=" + successStatus +
                ", answer='" + answer + '\'' +
                '}';
    }
}
