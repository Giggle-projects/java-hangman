package src.game;

import src.util.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class Game {

    private final int gameRound;
    private final int life;

    private Game(int gameRound, int life) {
        this.gameRound = gameRound;
        this.life = life;
    }

    public static Game createGame() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true){
            try {
                System.out.println(Message.MSG_GAME_START);
                String input = br.readLine();
                StringTokenizer st = new StringTokenizer(input, ",");

                int gameRound = Integer.parseInt(st.nextToken());
                int life = Integer.parseInt(st.nextToken());
                System.out.println(gameRound+ "," + life);

                return new Game(gameRound, life);
            } catch (NumberFormatException e){
                System.out.println(Message.ERR_MSG_INVALID_INPUT_NUMBER_FORMAT);
            } catch (NoSuchElementException e){
                System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
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
}
