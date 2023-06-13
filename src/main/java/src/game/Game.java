package src.game;

import src.util.Message;

import java.util.*;

import static src.util.Utils.inputGameRoundAndLife;

public class Game {
    private static final int MAX_GAME_ROUND = 20;

    private final int gameRound;
    private final int life;

    private Game(int gameRound, int life) {
        this.gameRound = gameRound;
        this.life = life;
    }

    public int getGameRound() {
        return gameRound;
    }

    public int getLife() {
        return life;
    }

    @Override
    public String toString() {
        return "Game{" +
                "gameRound=" + gameRound +
                ", life=" + life +
                '}';
    }

    public static Game createGame() {
        List<Integer> inputArr = inputGameRoundAndLife();

        int gameRound = inputArr.get(0);
        int life = inputArr.get(1);

        if (gameRound > MAX_GAME_ROUND) {
            System.out.println(Message.ERR_MSG_INVALID_INPUT_ROUND_RANGE);
            return createGame();
        }

        return new Game(gameRound, life);
    }
}
