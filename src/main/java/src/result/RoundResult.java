package src.result;

public class RoundResult {
    private int roundId;
    private int life;
    private String enteredAnswer;
    private char userInput;
    private int gameId;

    private RoundResult(int roundId, int life, String enteredAnswer, char userInput, int gameId) {
        this.roundId = roundId;
        this.life = life;
        this.enteredAnswer = enteredAnswer;
        this.userInput = userInput;
        this.gameId = gameId;
    }

    public static RoundResult createRoundResult(int roundId, int life, String enteredAnswer, char userInput, int gameId) {
        return new RoundResult(roundId, life, enteredAnswer, userInput, gameId);
    }

    public int getRoundId() {
        return roundId;
    }

    public int getLife() {
        return life;
    }

    public String getEnteredAnswer() {
        return enteredAnswer;
    }

    public char getUserInput() {
        return userInput;
    }

    public int getGameId() {
        return gameId;
    }

    @Override
    public String toString() {
        return "RoundResult{" +
                "roundId=" + roundId +
                ", life=" + life +
                ", enteredAnswer='" + enteredAnswer + '\'' +
                ", userInput=" + userInput +
                '}';
    }
}
