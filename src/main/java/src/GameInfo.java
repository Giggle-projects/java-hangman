package src;

import java.util.List;

public class GameInfo {
    private int gameId;
    private int lives;
    private boolean guess;
    private String answer;
    private List<RoundInfo> roundList;

    GameInfo(int gameId, String answer, boolean guess, int lives, List<RoundInfo> roundList) {
        this.gameId = gameId;
        this.answer = answer;
        this.guess = guess;
        this.lives = lives;
        this.roundList = roundList;
    }

    public String getGuessResult() {
        if (!this.guess) {
            return "실패";
        }
        return "성공";
    }

    public void displayGameResult() {
        System.out.println("=== Game Result ===");
        System.out.println("게임 id : "+ this.gameId + ", 추측 : " +  getGuessResult() + ", 남은 목숨 :" + this.lives + ", 정답 :" + this.answer + "\n");
        for (int k = 0; k < roundList.size(); k++) {
            System.out.println(roundList.get(k));
        }
        System.out.println("===================");
    }
}
