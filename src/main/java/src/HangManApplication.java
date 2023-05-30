package src;

import java.util.Scanner;

public class HangManApplication {
    private static final String [] LIST = {"apple", "banana", "orange"};
    private static Scanner scanner;
    private static int count;
    private static int life;

    public static void main(String[] args) {
        run();
        for (int i = 0; i < count; i++) {
            startGame(i + 1, life);
        }
    }

    private static void run(){
        scanner = new Scanner(System.in);
        System.out.println("ou : 게임 횟수와 목숨을 입력하세요.");
        count = scanner.nextInt();
        life = scanner.nextInt();
        System.out.printf("in : %d, %d%n", count, life);
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
        }
        if (correctCount == answerLength) {
            System.out.println("ou : 축하합니다. 정답입니다.");
        } else {
            System.out.println("\nou : 실패입니다.");
        }
    }

    private static char getInputData(int gameNumber, int life, char[] answerChange_) {
        System.out.printf("ou : %d 라운드 : %s , 목숨 %d %n" , gameNumber, String.valueOf(answerChange_), life);
        String inputDataString = scanner.next();
        char inputData = inputDataString.charAt(0);
        System.out.printf("in : %s ", inputData);
        return inputData;
    }

    private static String getRandomAnswer(){
        int index = (int) (Math.random() * LIST.length);
        System.out.println("정답 : " + LIST[index]);
        return LIST[index];
    }
}
