package src.result;

import java.util.ArrayList;
import java.util.List;

public class GameResult {

    private static int GAME_ID = 0;
    private String isWin;
    private int numOfLife;
    private String answer;
    private List<RoundResult> rounds;

    public GameResult() {

    }

    public void startGame() {
        GAME_ID++;
    }

    public GameResult(String isWin, int numOfLife, String answer) {
        GAME_ID++;
        this.isWin = isWin;
        this.numOfLife = numOfLife;
        this.answer = answer;
        this.rounds = new ArrayList<>();
    }

    public void addRound(RoundResult roundResult) {
        rounds.add(roundResult);
    }

    public void printGameResult() {
        System.out.println("=== Game Result ===");
        System.out.printf("게임 id : %d, 추측 : %s, 남은 목숨 : %d, 정답 : %s\n", GAME_ID, isWin, numOfLife, answer);
        for (RoundResult round : rounds) {
            System.out.printf("");
        }
    }

    public static int getGameId() {
        return GAME_ID;
    }

    public static void setGameId(int gameId) {
        GAME_ID = gameId;
    }

    public String getIsWin() {
        return isWin;
    }

    public void setIsWin(String isWin) {
        this.isWin = isWin;
    }

    public int getNumOfLife() {
        return numOfLife;
    }

    public void setNumOfLife(int numOfLife) {
        this.numOfLife = numOfLife;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public List<RoundResult> getRounds() {
        return rounds;
    }

    public void setRounds(List<RoundResult> rounds) {
        this.rounds = rounds;
    }
}
