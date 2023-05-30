package src;

import src.game.Game;
import src.game.Question;
import java.io.IOException;

public class HangManApplication {

    public static void main(String[] args) throws IOException {
        final Game game = Game.createGame();

        //TODO : 문제 예시를 만들어 랜덤문제 만들기
        String question = "apple";
        Question newRound = new Question(question);

        int life = game.getLife();

        for (int gameNum = 1; gameNum <= game.getGameRound(); gameNum++) {
            if (gameNum > 1)
                System.out.println("\n다음 게임을 시작합니다.");
            System.out.println(gameNum+"번째 게임이 시작됩니다. 정답 단어는 "
                    + newRound.getTargetQuestion().length() +"글자 입니다.");

            newRound.guessingWords(life);
        }
    }
}
