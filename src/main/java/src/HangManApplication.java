package src;

import src.result.GameResult;
import src.result.RoundResult;

import java.util.InputMismatchException;
import java.util.*;
import java.util.Scanner;

import static src.message.Message.ERR_MSG_INVALID;

public class HangManApplication {
    private static final String [] LIST = {"apple", "banana", "orange"};
    private static Scanner scanner;
    private static int count;
    private static int life;
    static List<GameResult> gameResults = new ArrayList<>();

    public static void main(String[] args) {
        run();
        for (int i = 0; i < count; i++) {
            startGame(i + 1, life);
        }
    }

    private static void run(){
        while (true){
            try{
                scanner = new Scanner(System.in);
                System.out.println("ou : 게임 횟수와 목숨을 입력하세요.");
                count = scanner.nextInt();
                life = scanner.nextInt();
                System.out.printf("in : %d, %d%n", count, life);
                break;
            }
            catch (InputMismatchException e){
                System.out.println(ERR_MSG_INVALID);
            }
        }
    }

    private static void startGame(int gameNumber , int life){
        String answer = getRandomAnswer();
        int answerLength = answer.length();
        char[] change = new char[answerLength];
        boolean[] checked = new boolean[answerLength];
        int correctCount = 0;

        for (int i = 0; i < answerLength; i++) {
            change[i] = '_';
        }

        System.out.printf("%d번째 게임이 시작됩니다. 정답 단어는 %d글자 입니다.%n", gameNumber, answerLength);

        List<RoundResult> roundResults = new ArrayList<>();

        while (correctCount < answerLength && life > 0){

            char inputData = getInputData(gameNumber, life, change);

            boolean found = false;
            for (int i = 0; i < answerLength; i++) {
                if (answer.charAt(i) == inputData){
                    if (!checked[i]){
                        checked[i] = true;
                        change[i] = inputData;
                        correctCount++;
                        found = true;
                    }
                }
            }

            if (!found) {
                life--;
            }

            roundResults.add(new RoundResult(gameNumber, life, String.valueOf(change), inputData));

        }

        boolean gameSuccess = (correctCount == answerLength);

        GameResult gameResult = new GameResult(gameNumber, gameSuccess, life, answer, roundResults);
        gameResults.add(gameResult);

        if (gameSuccess) {
            System.out.println("ou : 축하합니다. 정답입니다.");
        } else {
            System.out.println("\nou : 실패입니다.");
        }
        printGameResult(gameResult);
    }

    private static char getInputData(int gameNumber, int life, char[] answerChange_){
        System.out.printf("ou : %d 라운드 : %s , 목숨 %d %n" , gameNumber, String.valueOf(answerChange_), life);

        String inputDataString = scanner.next().toLowerCase();
        char inputData = inputDataString.charAt(0);
        System.out.printf("in : %s %n", inputData);
        return inputData;
    }

    private static String getRandomAnswer(){
        int index = (int) (Math.random() * LIST.length);
        return LIST[index];
    }

    private static void printGameResult(GameResult gameResult) {
        System.out.println("=== Game Result ===");
        System.out.printf("게임 id: %d, 추측: %s, 남은 목숨: %d, 정답: %s%n",
                gameResult.getId(), gameResult.isSuccess() ? "성공" : "실패", gameResult.getLife(), gameResult.getAnswer());
        System.out.println();

        List<RoundResult> roundResults = gameResult.getRoundResults();
        for (RoundResult roundResult : roundResults) {
            System.out.printf("라운드 id: %d, 남은 목숨: %d, %s, 사용자 입력: %s%n",
                    roundResult.getId(), roundResult.getLife(), roundResult.getAnswerChange(), roundResult.getInputData());
        }
        System.out.println("===================");
    }
}
