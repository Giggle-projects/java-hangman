package src;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Play{
    Scanner scanner = new Scanner(System.in);
    User user = new User();
    private static List<RoundInfo> roundInfos = new ArrayList<>();
    private static List<GameInfo> gameInfos = new ArrayList<>();
    private static boolean isSuccess;

    public void gameMenu(){
        while (true){
            System.out.println("메뉴를 선택합니다. (1 : 게임하기, 2 : 게임 결과 보기, 3 : 라운드 결과 보기, 4 : 게임종료)");
            int menuNum = scanner.nextInt();
            switch (menuNum) {
                case 1 :
                    playGame();
                    break;
                case 2 :
                    System.out.println("게임 id를 입력해주세요.");
                    int gameId = scanner.nextInt();
                    AllResult(gameId);
                    break;
                case 3 :
                    System.out.println("라운드 id를 입력해주세요.");
                    int roundId = scanner.nextInt();
                    RoundResult(roundId);
                    break;
                case 4 :
                    return;
                default:
                    System.out.println("잘못된 번호입니다.");
                    break;
            }
        }
    }



    public void playGame() {
        boolean isValidInput = false;
        int gameId = 1;
        while (!isValidInput){
            try {
                System.out.println("게임 횟수와 목숨을 입력하세요.");
                String[] input = scanner.nextLine().split(",");
                if (input.length < 2){
                    throw new IllegalArgumentException("숫자, 숫자 형태로 입력해주세요.");
                }
                int gameNum = Integer.parseInt(input[0].trim());
                int life = Integer.parseInt(input[1].trim());
                user.setGameNum(gameNum);
                user.setLife(life);
                isValidInput = true;
            } catch (NumberFormatException e){
                System.out.println("숫자, 숫자 형태로 입력해주세요.");
            } catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }

        int userLife = user.getLife();
        for (int i = 0; i < user.getGameNum(); i++){
            String answer = GameAnswer.setAnswer();
            char[] blind = GameAnswer.setBlind(answer);
            if (i != 0){
                System.out.println("\n다음 게임을 시작합니다.");
            }
            System.out.println(gameId + "번째 게임이 시작됩니다. 정답 단어는 " + answer.length() + "글자 입니다.");
            round(gameId, userLife, answer, blind, scanner);
            GameInfo gameInfo = new GameInfo(gameId, isSuccess, userLife, answer);
            gameInfos.add(gameInfo);
            gameId++;
        }
    }

    private static void round(int gameId, int userLife, String answer, char[] blind, Scanner scanner) {
        String blindScreen;

        GameInfo gameInfo = null;
        int roundId = 1;
        Set<Character> prevAlp = new HashSet<>();

        while (userLife > 0) {
            blindScreen = String.valueOf(blind);
            System.out.println(roundId + " 라운드 : " + blindScreen + ", 목숨 " + userLife);
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

                if (prevAlp.contains(ch)){
                    System.out.println("이미 입력하였던 알파벳입니다.");
                    isValid = true;
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

            isSuccess = true;
            for (char c : blind){
                if (c == '_'){
                    isSuccess = false;
                    break;
                }
            }

            RoundInfo result = new RoundInfo(roundId, userLife, blindScreen, ch);
            roundInfos.add(result);
            roundId++;

            if (isSuccess){
                System.out.println("축하합니다. 정답입니다.");
                break;
            }
            if (userLife == 0){
                System.out.println("정답 맞추기에 실패하셨습니다.");
            }
        }


    }

    private void AllResult(int gameId) {
        
    }

    public void RoundResult(int roundId) {
        System.out.println("=== Round Result ===");
        roundInfos.get(roundId - 1).PrintRoundResult();
    }
}
