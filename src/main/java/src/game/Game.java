package src.game;

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

    public static Game createGame() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true){
            try {
                System.out.println("게임 횟수와 목숨을 입력하세요.");
                String input = br.readLine();
                StringTokenizer st = new StringTokenizer(input, ",");

                int gameRound = Integer.parseInt(st.nextToken());
                int life = Integer.parseInt(st.nextToken());
                System.out.println(gameRound+ "," + life);

                return new Game(gameRound, life);
            } catch (NumberFormatException e){
                System.out.println("잘못된 입력입니다. 숫자로 다시 입력해주세요.");
            } catch (NoSuchElementException e){
                System.out.println("잘못된 입력입니다. 게임 횟수와 목숨을 ','로 구분하여 다시 입력해주세요.");
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
