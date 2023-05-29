package src.hangmanGame;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HangmanGameRoundTable implements Iterable<Integer>{

    private final Map<Integer, HangmanGameRoundInfo> roundTable;

    HangmanGameRoundTable() {
        roundTable = new HashMap<>();
    }

    public void saveRound(int round, HangmanGameRoundInfo roundInfo) {
        roundTable.put(round, roundInfo);
    }

    public HangmanGameRoundInfo getRound(int round) {
        return roundTable.get(round);
    }

    @Override
    public Iterator<Integer> iterator() {
        return roundTable.keySet().iterator();
    }
}
