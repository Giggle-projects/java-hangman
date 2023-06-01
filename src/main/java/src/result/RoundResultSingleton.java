package src.result;

import src.util.Message;

import java.util.ArrayList;
import java.util.List;

public class RoundResultSingleton {
    private static final RoundResultSingleton instance = new RoundResultSingleton();
    private final List<RoundResult> roundResultList;

    private RoundResultSingleton() {
        roundResultList = new ArrayList<>();
    }

    public static RoundResultSingleton getInstance(){
        return instance;
    }

    public void addRoundResult(RoundResult roundResult){
        roundResultList.add(roundResult);
    }

    public RoundResult getByGameIdAndRoundId(int gameId, int roundId){

        for (RoundResult roundResult : roundResultList) {
            if (roundResult.getGameId() == gameId
                    && roundResult.getRoundId() == roundId) {
                return roundResult;
            }
        }
        return null;
    }

    public String printRoundResult(int gameId) {
        StringBuilder result = new StringBuilder();
        for (RoundResult roundResult : roundResultList) {
            if (roundResult.getGameId() == gameId) {
                result.append("라운드 ID : ")
                        .append(roundResult.getGameId()).append("-").append(roundResult.getRoundId())
                        .append(", 남은 목숨 : ")
                        .append(roundResult.getLife())
                        .append(", 밝혀진 단어 : ")
                        .append(roundResult.getEnteredAnswer())
                        .append(", 사용자 입력 : ")
                        .append(roundResult.getUserInput())
                        .append('\n');
            }
        }
        return result.toString();
    }

    public String printRoundResult(int gameId, int roundId){
        StringBuilder result = new StringBuilder();
        RoundResult roundResult = getByGameIdAndRoundId(gameId, roundId);

        if (roundResult != null){
            result.append("라운드 ID : ")
                    .append(roundResult.getGameId()).append("-").append(roundResult.getRoundId())
                    .append(", 남은 목숨 : ")
                    .append(roundResult.getLife())
                    .append(", 밝혀진 단어 : ")
                    .append(roundResult.getEnteredAnswer())
                    .append(", 사용자 입력 : ")
                    .append(roundResult.getUserInput())
                    .append('\n');
        } else {
            result.append(Message.ERR_MSG_NOT_FOUND_DATA);
        }

        return result.toString();

    }
}
