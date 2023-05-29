package src.hangmanGame;

import java.util.Iterator;
import java.util.Map;

public class HangmanGameRoundTable implements Iterable<Integer> {

    private final Map<Integer, HangmanGameRoundInfo> roundTable;
    private String gameResult;

    public HangmanGameRoundTable(Map<Integer, HangmanGameRoundInfo> roundTable) {
        this.roundTable = roundTable;
    }

    public void saveRound(int roundId, HangmanGameRoundInfo roundInfo) {
        roundTable.put(roundId, roundInfo);
    }

    public HangmanGameRoundInfo getRound(int roundId) throws IllegalArgumentException {
        if (roundId > roundTable.size()) throw new IllegalArgumentException("해당 라운드 정보는 존재하지 않습니다.");
        return roundTable.get(roundId);
    }

    public void saveGameResult(String gameResult) {
        this.gameResult = gameResult;
    }

    public String gameResutToString() {
        StringBuilder result = new StringBuilder();
        result.append("\n=== Game Result ===\n").append(gameResult);
        for (Integer roundId : roundTable.keySet()){
            result.append(roundTable.get(roundId).toString());
        }
        return result.append("===================\n").toString();
    }

    public boolean isEmpty() {
        return roundTable.isEmpty();
    }

    @Override
    public Iterator<Integer> iterator() {
        return roundTable.keySet().iterator();
    }

    static class HangmanGameRoundInfo {

        private static final String INFO_PRINT_FORMAT = "남은 목숨 : %d, %s, 사용자 입력 : %c\n";

        public final int remainingLife;
        public final String correctingWord;
        public final char inputAlphabet;

        public HangmanGameRoundInfo(int remainingLife, String correctingWord, char inputAlphabet) {
            this.remainingLife = remainingLife;
            this.correctingWord = correctingWord;
            this.inputAlphabet = inputAlphabet;
        }

        @Override
        public String toString() {
            return String.format(INFO_PRINT_FORMAT, remainingLife, correctingWord, inputAlphabet);
        }
    }
}
