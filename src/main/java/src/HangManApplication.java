package src;

import src.game.Game;
import src.game.Question;
import src.result.GameResult;
import src.result.GameResultSingleton;
import src.result.RoundResult;
import src.result.RoundResultSingleton;
import src.util.Menu;
import src.util.Message;
import src.util.Utils;

import java.io.IOException;
import java.util.List;

import static src.game.Game.createProblems;
import static src.util.InputMenu.chooseCategory;
import static src.util.InputMenu.chooseMenu;

public class HangManApplication {
    static GameResultSingleton gameResultSingleton = GameResultSingleton.getInstance();
    static RoundResultSingleton roundResultSingleton = RoundResultSingleton.getInstance();

    public static void main(String[] args) {

        while (true){
            final Menu menu = chooseMenu();

            if (menu == Menu.PLAY_GAME){
                System.out.println(Message.MSG_GAME_START);
                Game game = Game.createGame();
                String categoryName = chooseCategory();
                List<String> problems = createProblems(game.getGameRound(), categoryName);
                int life = game.getLife();

                playHangmanGame(game, problems, life);
            }


            if (menu == Menu.SHOW_GAME_RESULT) {
                System.out.println(Message.MSG_INPUT_GAME_ID);
                final int gameId = Utils.getInt();

                System.out.println(gameResultSingleton.printGameResult(gameId));
            }

            if (menu == Menu.SHOW_ROUND_RESULT) {
                System.out.println(Message.MSG_INPUT_GAME_ID);
                final int gameId = Utils.getInt();
                System.out.println(Message.MSG_INPUT_ROUND_ID);
                final int roundId = Utils.getInt();

                System.out.println(roundResultSingleton.printRoundResult(gameId, roundId));
            }

            if (menu.isEnd()){
                break;
            }
        }
    }

    private static void playHangmanGame(Game game, List<String> problems, int life) {
        for (int gameNum = 1; gameNum <= game.getGameRound(); gameNum++) {
            if (gameNum > 1)
                System.out.println(Message.MSG_NEXT_GAME);

            String problem = problems.get(gameNum);

            System.out.println(gameNum+"번째 게임이 시작됩니다. 정답 단어는 "
                    + problem.length() +"글자 입니다.");

            guessingWords(life, problem);
        }
    }

    public static void guessingWords(int life, String problem) {
        GameResult gameResult;
        RoundResult roundResult;
        int gameSeqNum = GameResult.getGameSeqNum();
        System.out.println(gameSeqNum);
        int round = 1;

        Question newRound = new Question(problem);
        System.out.println(newRound);

        String enteredAnswer = newRound.getEnteredAnswer();

        while (life > -1) {
            System.out.println(round + " 라운드 : " + enteredAnswer + ", 목숨 " + life);

            char targetChar = Utils.getChar();
            StringBuilder answer = new StringBuilder(enteredAnswer);

            if (newRound.getTargetQuestion().indexOf(targetChar) == -1)
                life--;
            else {
                for (int i = 0; i < newRound.getTargetQuestion().length(); i++) {
                    if (newRound.getTargetQuestion().charAt(i) == targetChar) {
                        answer.setCharAt(i, targetChar);
                    }
                }
            }
            enteredAnswer = answer.toString();

            roundResult = RoundResult.createRoundResult(round, life, enteredAnswer, targetChar, gameSeqNum);
            roundResultSingleton.addRoundResult(roundResult);

            round++;

            if (newRound.getTargetQuestion().contentEquals(enteredAnswer)) {
                System.out.println(round + " 라운드 : " + enteredAnswer + ", 목숨 " + life);
                System.out.println(Message.MSG_ROUND_CLEAR);
                gameResult = GameResult.createGameResult(life, true, newRound.getTargetQuestion());
                gameResultSingleton.addGameResult(gameResult);
                life = -1;
            }
            if (life == 0){
                System.out.println(Message.MSG_ROUND_OVER);
                gameResult = GameResult.createGameResult(life, false, newRound.getTargetQuestion());
                gameResultSingleton.addGameResult(gameResult);
                life = -1;
            }
        }
        System.out.println(gameResultSingleton.printGameResult(gameSeqNum));
        System.out.println(roundResultSingleton.printRoundResult(gameSeqNum));
    }
}
