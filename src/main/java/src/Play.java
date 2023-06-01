package src;

import java.util.Arrays;
import java.util.Scanner;

public class Play{

    public void playGame() {
        User user = new User();
        System.out.println("게임 횟수와 목숨을 입력하세요.");
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split(",");
        user.setGameNum(Integer.parseInt(input[0].trim()));
        user.setLife(Integer.parseInt(input[1].trim()));

        int userLife = user.getLife();
        for (int i = 0; i < user.getGameNum(); i++){
            String answer = Set.setAnswer();
            char[] blind = Set.setBlind(answer);
            if (i != 0){
                System.out.println("\n다음 게임을 시작합니다.");
            }
            System.out.println((i + 1) + "번째 게임이 시작됩니다. 정답 단어는 " + answer.length() + "글자 입니다.");
            round(userLife, answer, blind, scanner);
        }

    }

    private static void round(int userLife, String answer, char[] blind, Scanner scanner) {
        for (int i = 1; userLife > 0; i++){
            String blindScreen = String.valueOf(blind);
            System.out.println(i + " 라운드 : " + blindScreen + ", 목숨 " + userLife);
            boolean isValid = true;
            char ch = '\0';
            while (isValid){
                ch = scanner.next().charAt(0);
                if (Character.isLowerCase(ch)) {
                    isValid = false;
                } else if (Character.isUpperCase(ch)) {
                    ch = Character.toLowerCase(ch);
                    isValid = false;
                } else {
                    System.out.println("알파벳을 입력해주세요.");
                }
            }

            boolean isMatch = false;
            for (int j = 0; j < answer.length(); j++){
                if (answer.charAt(j) == ch){
                    blind[j] = ch;
                    isMatch = true;
                }
            }
            if (!isMatch){
                userLife--;
            }

            boolean isBlind = true;
            for (char c : blind){
                if (c == '_'){
                    isBlind = false;
                    break;
                }
            }
            if (isBlind){
                System.out.println("축하합니다. 정답입니다.");
                break;
            }
            if (userLife == 0){
                System.out.println("정답 맞추기에 실패하셨습니다.");
            }
        }
    }
}
