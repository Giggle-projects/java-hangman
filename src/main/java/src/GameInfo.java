package src;

import java.util.List;

public class GameInfo {
    private int gameId;
    private int lives;
    private boolean isGameSuccess;
    private String answer;
    private List<RoundInfo> roundList;

    public GameInfo(int gameId, String answer, boolean isGameSuccess, int lives, List<RoundInfo> roundList) {
        this.gameId = gameId;
        this.answer = answer;
        this.isGameSuccess = isGameSuccess;
        this.lives = lives;
        this.roundList = roundList;
    }

    public String getGuessResult() {
        if (!this.isGameSuccess) {
            return "실패";
        }
        return "성공";
    }

    public void displayGameResult() {
        System.out.println("=== Game Result ===");
        System.out.println("게임 id : "+ this.gameId + ", 추측 : " +  getGuessResult() + ", 남은 목숨 :" + this.lives + ", 정답 :" + this.answer + "\n");
        for (int index = 0; index < roundList.size(); index++) {
            System.out.println(roundList.get(index));
        }
        System.out.println("===================");
    }
}
