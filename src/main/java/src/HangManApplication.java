package src;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class HangManApplication {

    public static void main(String[] args) {
        int gameEndCount = 2;
        int gameLife = 4;
        System.out.println("게임 횟수와 목숨을 입력하세요.");

        String apple = "apple";

        int gameCount = 1;
        while (true) {
            System.out.println(gameCount + "번째 게임이 시작됩니다. 정답 단어는 5글자 입니다.");
            System.out.println("ou : 1 라운드 : _____, 목숨 4");
            loop(apple, gameLife);
            gameCount++;
            if (gameCount > gameEndCount) {
                break;
            }
            System.out.println("다음 게임을 시작합니다.");
        }

    }

    public static void loop(String apple, int gameLife) {
        int round = 1;
        Scanner sc = new Scanner(System.in);
        StringBuilder under = new StringBuilder("-----");
        boolean game = true;
        while (game) {
            char input = sc.next().charAt(0);
            setCorrectWord(apple, under, input);
            if (!isUserCorrect(apple, input)) {
                gameLife--;
            }
            round++;
            System.out.println("ou : " + round + " 라운드 : " + under + "목숨 " + gameLife);

            String answer = apple.toString();
            if (answer.equals(under.toString())) {
                System.out.println("축하합니다. 정답입니다.");
                game = false;
            }
            if (gameLife == 0) {
                System.out.println("Life가 0입니다.");
                game = false;
            }
        }

    }

    private static void setCorrectWord(String apple, StringBuilder under, char input) {
        for (int i = 0; i < apple.length(); i++) {
            if (apple.charAt(i) == input) {
                under.setCharAt(i, input);
            }
        }
    }

    private static boolean isUserCorrect(String apple, char input) {
        for (char c : apple.toCharArray()) {
            if (c == input) {
                return true;
            }
        }
        return false;
    }
}
