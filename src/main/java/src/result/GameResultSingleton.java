package src.result;

import src.util.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public Optional<GameResult> getByGameId(int gameId){

        for (GameResult gameResult : gameResultList) {
            if (gameResult.getGameNum() == gameId) {
                return Optional.of(gameResult);
            }
        }
        return Optional.empty();
    }

    @Override
    public String toString() {
        return "GameResultSingleton{" +
                "gameResultList=" + gameResultList +
                '}';

    }

    public void printGameResult(int gameId){
        StringBuilder result = new StringBuilder();
        Optional<GameResult> gameResult = getByGameId(gameId);

        if (gameResult.isEmpty()){
            System.out.println(Message.ERR_MSG_NOT_FOUND_DATA);
        }

        if (gameResult.isPresent()){
            result.append("게임 ID : ")
                    .append(gameResult.get().getGameNum()).append(", 추측 : ").append(gameResult.get().isSuccessStatus() ? "성공" : "실패")
                    .append(", 남은 목숨 : ")
                    .append(gameResult.get().getRemainingLives())
                    .append(", 정답 : ")
                    .append(gameResult.get().getAnswer())
                    .append('\n');

            System.out.println(result);
        }

    }
}
