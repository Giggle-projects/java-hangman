package src.result;

import src.util.Message;

import java.util.ArrayList;
import java.util.List;

public class GameResultSingleton {
    private static final GameResultSingleton instance = new GameResultSingleton();
    private final List<GameResult> gameResultList;

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

    public GameResult getByGameId(int gameId){

        for (GameResult gameResult : gameResultList) {
            if (gameResult.getGameNum() == gameId) {
                return gameResult;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "GameResultSingleton{" +
                "gameResultList=" + gameResultList +
                '}';

    }

    public String printGameResult(int gameId){
        StringBuilder result = new StringBuilder();
        GameResult gameResult = getByGameId(gameId);

        if (gameResult != null) {
        result.append("게임 ID : ")
                .append(gameResult.getGameNum()).append(", 추측 : ").append(gameResult.isSuccessStatus() ? "성공" : "실패")
                .append(", 남은 목숨 : ")
                .append(gameResult.getRemainingLives())
                .append(", 정답 : ")
                .append(gameResult.getAnswer())
                .append('\n');
        } else {
            result.append(Message.ERR_MSG_NOT_FOUND_DATA);
        }

        return result.toString();
    }
}
