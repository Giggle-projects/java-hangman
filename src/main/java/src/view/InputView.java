package src.view;

import src.hangmanGame.HangmanGameInfo;
import src.util.Console;

public class InputView {

    private static final String OU = "ou : ";
    private static final String IN = "in : ";

    private static final String NUMBER_OF_GAME_AND_LIFE = "게임 횟수와 목숨을 입력하세요.";

    public static HangmanGameInfo inputHangmanGameInfo() throws IllegalArgumentException {
        String input = inputOf(NUMBER_OF_GAME_AND_LIFE);
        String gameNumberAndLife = input.replaceAll("[^a-zA-Z0-9가-힣,]", "");

        String numberGames = gameNumberAndLife.split(",")[0];
        String life = gameNumberAndLife.split(",")[1];

        return new HangmanGameInfo(numberGames, life);
    }

    /**
     * 파라미터로 넘어온 메시지를 출력하고 입력 값을 반환한다.
     * @param message 상황에 맞는 입력값을 받기 위해 출력할 메시지
     * @return 사용자가 입력한 문자열
     * */
    private static String inputOf(String message){
        System.out.println(OU + message);
        System.out.print(IN);
        return Console.readLine();
    }
}
