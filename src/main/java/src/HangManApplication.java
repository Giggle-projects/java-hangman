package src;

import src.game.Game;
import src.game.Question;
import src.result.GameResult;
import src.result.GameResultSingleton;
import src.result.RoundResult;
import src.result.RoundResultSingleton;
import src.util.Message;
import src.util.Utils;

import java.util.List;

public class HangManApplication {
    static GameResultSingleton gameResultSingleton = GameResultSingleton.getInstance();
    static RoundResultSingleton roundResultSingleton = RoundResultSingleton.getInstance();

    public static void main(String[] args) {
        Game game = Game.createGame();
        List<String> exampleList = Game.chooseCategory(game.getGameRound());
        int life = game.getLife();

        playHangmanGame(game, exampleList, life);
        printGameResult();
    }

    private static void playHangmanGame(Game game, List<String> exampleList, int life) {
        for (int gameNum = 1; gameNum <= game.getGameRound(); gameNum++) {
            if (gameNum > 1)
                System.out.println(Message.MSG_NEXT_GAME);

            String example = exampleList.get(gameNum);

            System.out.println(gameNum+"번째 게임이 시작됩니다. 정답 단어는 "
                    + example.length() +"글자 입니다.");

            guessingWords(gameNum, life, example);
        }
    }

    public static void guessingWords(int gameNum, int life, String example){
        GameResult gameResult;
        RoundResult roundResult;
        int round = 1;

        Question newRound = new Question(example);
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

            roundResult = RoundResult.createRoundResult(round, life, enteredAnswer, targetChar, gameNum);
            roundResultSingleton.addRoundResult(roundResult);

            round++;

            if (newRound.getTargetQuestion().contentEquals(enteredAnswer)) {
                System.out.println(round + " 라운드 : " + enteredAnswer + ", 목숨 " + life);
                System.out.println(Message.MSG_ROUND_CLEAR);
                gameResult = GameResult.createGameResult(gameNum, life, true, newRound.getTargetQuestion());
                gameResultSingleton.addGameResult(gameResult);
                life = -1;
            }

            if (life == 0){
                System.out.println(Message.MSG_ROUND_OVER);
                gameResult = GameResult.createGameResult(gameNum, life, false, newRound.getTargetQuestion());
                gameResultSingleton.addGameResult(gameResult);
                life = -1;
            }
        }
    }

    public static void printGameResult(){
        System.out.println(gameResultSingleton.printGameResult());
        System.out.println(roundResultSingleton.printRoundResult());
    }
}
