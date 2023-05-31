package src.hangmanGame;

import java.util.Map;

public class HangmanGameTable {

    private final Map<Integer, HangmanGameRoundTable> gameTable;

    public HangmanGameTable(Map<Integer, HangmanGameRoundTable> gameTable) {
        this.gameTable = gameTable;
    }

    public HangmanGameRoundTable getRoundTable(int gameId) throws IllegalArgumentException {
        return gameTable.get(gameId);
    }

    public HangmanGameRoundTable getRoundTableWithException(int gameId) throws IllegalArgumentException {
        if (gameId > gameTable.size()) throw new IllegalArgumentException("존재하지 않는 게임 회차 정보입니다.");
        HangmanGameRoundTable roundTable = gameTable.get(gameId);
        if (roundTable.isEmpty()) throw new IllegalArgumentException("아직 진행하지 않은 게임의 정보는 조회할 수 없습니다.");
        return roundTable;
    }
}
