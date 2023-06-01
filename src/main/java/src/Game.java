package src;


public class Game {
    private int gameId;
    private boolean isBlind;
    private int userLife;
    private String answer;

    public Game(int gameId, boolean isBlind, int userLife, String answer) {
        this.gameId = gameId;
        this.isBlind = isBlind;
        this.userLife = userLife;
        this.answer = answer;
    }

    public void PrintGameResult(){
        String result = isBlind ? "성공" : "실패";
        System.out.println("=== Game Result ===");
        System.out.println("게임 id : " + gameId + ", 추측 : " + result + ", 남은 목숨 : " + userLife + ", 정답 : " + answer);
    }

}
