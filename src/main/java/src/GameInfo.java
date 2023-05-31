package src;

import java.util.List;

public class GameInfo {
    private int gameId;
    private int lives;
    private boolean guess;
    private String answer;
    private List<Round> roundList;


    GameInfo(int gameId, String answer, boolean guess, int lives, List<Round> roundList) {
        this.gameId = gameId;
        this.answer = answer;
        this.guess = guess;
        this.lives = lives;
        this.roundList = roundList;
    }

    public int getGameId() {
        return ++gameId;
    }


    public String getGuess() {
        if (!this.guess) {
            return "실패";
        }
        return "성공";
    }

    public int getLives() {
        roundList.get(roundList.size()-1);
        return 1;
    }

    public void printGameResult() {
        System.out.println("=== Game Result ===");
        System.out.println("게임 id : "+ getGameId() + ", 추측 : " +  getGuess() + ", 남은 목숨 :" + this.lives + ", 정답 :" + this.answer + "\n");
        for (int k = 0; k < roundList.size(); k++) {
            System.out.println(roundList.get(k));
        }
        System.out.println("===================");
    }


}
