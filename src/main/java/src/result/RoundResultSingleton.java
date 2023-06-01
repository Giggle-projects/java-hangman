package src.result;

import java.util.ArrayList;
import java.util.List;

public class RoundResultSingleton {
    private static final RoundResultSingleton instance = new RoundResultSingleton();
    private List<RoundResult> roundResultList;

    private RoundResultSingleton() {
        roundResultList = new ArrayList<>();
    }

    public static RoundResultSingleton getInstance(){
        return instance;
    }

    public List<RoundResult> getRoundResultList() {
        return roundResultList;
    }

    public void addRoundResult(RoundResult roundResult){
        roundResultList.add(roundResult);
    }

    public String printRoundResult() {
        StringBuilder result = new StringBuilder();
        for (RoundResult roundResult : roundResultList) {
            result.append("라운드 ID : ")
                    .append(roundResult.getGameId() + "-" + roundResult.getRoundId())
                    .append(", 남은 목숨 : ")
                    .append(roundResult.getLife())
                    .append(", 밝혀진 단어 : ")
                    .append(roundResult.getEnteredAnswer())
                    .append(", 사용자 입력 : ")
                    .append(roundResult.getUserInput())
                    .append('\n');
        }
        return result.toString();
    }
}
