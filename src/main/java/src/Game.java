package src;


import java.util.ArrayList;
import java.util.List;

public class Game {
    private int gameId;
    private boolean isBlind;
    private int userLife;
    private String answer;
    private List<Round> rounds;

    public Game(int gameId, boolean isBlind, int userLife, String answer, List<Round> rounds) {
        this.gameId = gameId;
        this.isBlind = isBlind;
        this.userLife = userLife;
        this.answer = answer;
        this.rounds = new ArrayList<>(rounds);
    }

    public List<Round> getRounds(){
        return rounds;
    }

    public void printGameResult(){
        String result = isBlind ? "성공" : "실패";
        System.out.println("=== Game Result ===");
        System.out.println("게임 id : " + gameId
                + ", 추측 : " + result
                + ", 남은 목숨 : " + userLife
                + ", 정답 : " + answer);
    }
}
