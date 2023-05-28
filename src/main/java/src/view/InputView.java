package src.view;

import src.hangmanGame.HangmanInfo;
import src.util.Console;

public class InputView {

    private static final String OU = "ou : ";
    private static final String IN = "in : ";

    private static final String BLANK = "";
    private static final String COMMA = ",";

    private static final String REGEX_REMOVE_WITHOUT_STRING_COMMA = "[^a-zA-Z0-9가-힣,]";
    private static final String REGEX_REMOVE_WITHOUT_ALPHABET = "[^a-zA-Z]";

    private static final int INDEX_NUMBER_GAMES = 0;
    private static final int INDEX_LIFE = 1;

    private static final String NUMBER_OF_GAME_AND_LIFE = "게임 횟수와 목숨을 입력하세요.";

    // Suppresses default constructor, ensuring non-instantiability.
    private InputView() {}

    public static HangmanInfo inputHangmanGameInfo() throws IllegalArgumentException {
        String input = inputOf(NUMBER_OF_GAME_AND_LIFE);
        String filteredInput = input.replaceAll(REGEX_REMOVE_WITHOUT_STRING_COMMA, BLANK);
        String[] gameNumberAndLife = filteredInput.split(COMMA);

        if(gameNumberAndLife.length != 2) throw new IllegalArgumentException("입력 값이 잘못 되었습니다.");

        String numberGames = gameNumberAndLife[INDEX_NUMBER_GAMES];
        String life = gameNumberAndLife[INDEX_LIFE];

        return new HangmanInfo(numberGames, life);
    }

    public static char inputAlphabet(String roundInfo) throws IllegalArgumentException {
        String input = inputOf(roundInfo);
        String filteredInput = input.replaceAll(REGEX_REMOVE_WITHOUT_ALPHABET, BLANK);

        if (filteredInput.length() != 1) throw new IllegalArgumentException("하나의 알파벳만 입력해 주세요.");

        return filteredInput.charAt(0);
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
