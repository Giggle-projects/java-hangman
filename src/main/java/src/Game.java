package src;

public class Game {
    private static Game game;


    public static Game getInstance() {
        if (game == null) {
            return new Game();
        }
        return game;
    }

    private Game() {

    }

    public int[] initGame() {
        System.out.printf("게임 횟수와 목숨을 입력하세요 : ");
        return Input.getInitiationParam();
    }

    public void startGame(int numOfGame, int numOfLife) {
        int numOfRound = 1;
        System.out.printf("%d번째 게임이 시작됩니다. 정답 단어는 %d글자 입니다.\n", 1, 1);
    }

}
