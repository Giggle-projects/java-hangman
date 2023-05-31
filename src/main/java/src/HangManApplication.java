package src;

import src.game.Game;
import src.game.Question;
import src.util.Message;

import java.io.IOException;
import java.util.List;

public class HangManApplication {

    public static void main(String[] args) {
        Game game = Game.createGame();
        List<String> exampleList = Game.chooseCategory(game.getGameRound());
        int life = game.getLife();

        for (int gameNum = 1; gameNum <= game.getGameRound(); gameNum++) {
            if (gameNum > 1)
                System.out.println(Message.MSG_NEXT_GAME);

            String example = exampleList.get(gameNum);
            Question newRound = new Question(example);

            System.out.println(gameNum+"번째 게임이 시작됩니다. 정답 단어는 "
                    + newRound.getTargetQuestion().length() +"글자 입니다.");

            newRound.guessingWords(life);
        }
    }
}
