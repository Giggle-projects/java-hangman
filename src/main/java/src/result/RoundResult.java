package src.result;

public class RoundResult {
    private int gameId;
    private int roundId;
    private int life;
    private String answerChange;
    private char inputData;

    public RoundResult(int gameId, int roundId, int life, String answerChange, char inputData) {
        this.gameId = gameId;
        this.roundId = roundId;
        this.life = life;
        this.answerChange = answerChange;
        this.inputData = inputData;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getRoundId() {
        return roundId;
    }

    public void setRoundId(int roundId) {
        this.roundId = roundId;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public String getAnswerChange() {
        return answerChange;
    }

    public void setAnswerChange(String answerChange) {
        this.answerChange = answerChange;
    }

    public char getInputData() {
        return inputData;
    }

    public void setInputData(char inputData) {
        this.inputData = inputData;
    }
}
