package src.result;

import java.util.ArrayList;
import java.util.List;

public class GameResultSingleton {
    private static final GameResultSingleton instance = new GameResultSingleton();
    private List<GameResult> gameResultList;

    private GameResultSingleton() {
        gameResultList = new ArrayList<>();
    }

    public static GameResultSingleton getInstance(){
        return instance;
    }

    public List<GameResult> getGameResultList() {
        return gameResultList;
    }

    public void addGameResult(GameResult gameResult){
        gameResultList.add(gameResult);
    }

    @Override
    public String toString() {
        return "GameResultSingleton{" +
                "gameResultList=" + gameResultList +
                '}';

    }

    public String printGameResult(){
        StringBuilder result = new StringBuilder();
        for (GameResult gameResult : gameResultList) {
            result.append("게임 ID : ")
                    .append(gameResult.getGameId()).append(", 추측 : ").append(gameResult.isSuccessStatus() ? "성공" : "실패")
                    .append(", 남은 목숨 : ")
                    .append(gameResult.getRemainingLives())
                    .append(", 정답 : ")
                    .append(gameResult.getAnswer())
                    .append('\n');
        }
        return result.toString();
    }
}
