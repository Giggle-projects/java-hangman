package src.result;

import src.util.Message;
import src.util.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public Optional<RoundResult> getByGameIdAndRoundId(int gameId, int roundId){

        for (RoundResult roundResult : roundResultList) {
            if (roundResult.getGameId() == gameId
                    && roundResult.getRoundId() == roundId) {
                return Optional.of(roundResult);
            }
        }
        return Optional.empty();
    }

    public void printRoundResult(int gameId) {
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
        System.out.println(result);
    }

    public void printRoundResult(){
        System.out.println(Message.MSG_INPUT_GAME_ID);
        final int gameId = Utils.getInt();
        System.out.println(Message.MSG_INPUT_ROUND_ID);
        final int roundId = Utils.getInt();

        StringBuilder result = new StringBuilder();
        Optional<RoundResult> roundResult = getByGameIdAndRoundId(gameId, roundId);

        if (roundResult.isEmpty())
            System.out.println(Message.ERR_MSG_NOT_FOUND_DATA);

        if (roundResult.isPresent()){
            result.append("라운드 ID : ")
                    .append(roundResult.get().getGameId()).append("-").append(roundResult.get().getRoundId())
                    .append(", 남은 목숨 : ")
                    .append(roundResult.get().getLife())
                    .append(", 밝혀진 단어 : ")
                    .append(roundResult.get().getEnteredAnswer())
                    .append(", 사용자 입력 : ")
                    .append(roundResult.get().getUserInput())
                    .append('\n');

            System.out.println(result);
        }

    }
}
