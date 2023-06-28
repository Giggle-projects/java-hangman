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

import java.util.List;

import static src.problem.ProblemList.getProblemsByProblemType;
import static src.util.InputMenu.chooseProblemType;
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
                String problemType = chooseProblemType();
                List<String> problems = getProblemsByProblemType(problemType);

                playHangmanGame(game, problems);
            }


            if (menu == Menu.SHOW_GAME_RESULT) {
                System.out.println(Message.MSG_INPUT_GAME_ID);
                final int gameId = Utils.getInt();
                gameResultSingleton.printGameResult(gameId);
            }

            if (menu == Menu.SHOW_ROUND_RESULT) {
                roundResultSingleton.printRoundResult();
            }

            if (menu.isEnd()){
                break;
            }
        }
    }

    private static void playHangmanGame(Game game, List<String> problems) {
        for (int gameNum = 1; gameNum <= game.getGameRound(); gameNum++) {
            if (gameNum > 1)
                System.out.println(Message.MSG_NEXT_GAME);

            String problem = problems.get(gameNum);

            System.out.println(gameNum+"번째 게임이 시작됩니다. 정답 단어는 "
                    + problem.length() +"글자 입니다.");

            guessingWords(game.getLife(), problem);
        }
    }

    public static void guessingWords(int life, String problem) {
        GameResult gameResult;
        RoundResult roundResult;

        int gameSeqNum = GameResult.getGameSeqNum();
        int round = 1;

        Question newRound = new Question(problem);
        String enteredAnswer = newRound.getEnteredAnswer();

        while (checkGameStatus(newRound, enteredAnswer, life)) {
            System.out.println(round + " 라운드 : " + enteredAnswer + ", 목숨 " + life);

            char targetChar = Utils.getChar();
            StringBuilder answer = new StringBuilder(enteredAnswer);

            if (newRound.checkCharInWord(targetChar))
                life--;

            for (int i = 0; i < problem.length(); i++) {
                if (problem.charAt(i) == targetChar) {
                    answer.setCharAt(i, targetChar);
                }
            }

            enteredAnswer = answer.toString();

            roundResult = RoundResult.createRoundResult(round, life, enteredAnswer, targetChar, gameSeqNum);
            roundResultSingleton.addRoundResult(roundResult);
            round++;
        }
        System.out.println(round + " 라운드 : " + enteredAnswer + ", 목숨 " + life);

        gameResult = GameResult.createGameResult(life, true, newRound.getTargetQuestion());
        endGame(gameResult, gameSeqNum);
    }

    private static void endGame(GameResult gameResult, int gameSeqNum) {
        gameResultSingleton.addGameResult(gameResult);
        gameResultSingleton.printGameResult(gameSeqNum);
        roundResultSingleton.printRoundResult(gameSeqNum);
    }

    public static boolean checkGameStatus(Question question, String enteredAnswer, int life) {
        if (question.validAnswer(enteredAnswer)) {
            System.out.println(Message.MSG_ROUND_CLEAR);
            return false;
        }
        if (life == 0){
            System.out.println(Message.MSG_ROUND_OVER);
            return false;
        }
        return true;
    }
}
