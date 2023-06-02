package src.result;

import java.util.List;

public class GameResult {
    private int id;
    private boolean success;
    private int life;
    private String answer;
    private List<RoundResult> roundResults;

    public GameResult(int id, boolean success, int life, String answer, List<RoundResult> roundResults) {
        this.id = id;
        this.success = success;
        this.life = life;
        this.answer = answer;
        this.roundResults = roundResults;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public List<RoundResult> getRoundResults() {
        return roundResults;
    }

    public void setRoundResults(List<RoundResult> roundResults) {
        this.roundResults = roundResults;
    }

    public static GameResult findGameResultById(List<GameResult> gameResults, int gameId) {
        for (GameResult gameResult : gameResults) {
            if (gameResult.getId() == gameId) {
                return gameResult;
            }
        }
        return null;
    }
}
