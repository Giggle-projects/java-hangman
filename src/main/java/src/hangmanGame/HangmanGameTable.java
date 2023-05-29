package src.hangmanGame;

import java.util.Map;

public class HangmanGameTable {

    private final Map<Integer, HangmanGameRoundTable> gameTable;

    public HangmanGameTable(Map<Integer, HangmanGameRoundTable> gameTable) {
        this.gameTable = gameTable;
    }

    public HangmanGameRoundTable getRoundTable(int round) {
        return gameTable.get(round);
    }
}
