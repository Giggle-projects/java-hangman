package src.view;

import src.hangmanGame.Hangman;
import src.util.Console;

public class InputView {

    private static final String OU = "ou : ";
    private static final String IN = "in : ";

    private static final String BLANK = "";
    private static final String COMMA = ",";

    private static final String REGEX_REMOVE_WITHOUT_NUMBER_AND_COMMA = "[^0-9,]";
    private static final String REGEX_REMOVE_WITHOUT_ALPHABET = "[^a-zA-Z]";

    private static final int INDEX_NUMBER_GAMES = 0;
    private static final int INDEX_LIFE = 1;

    private static final String NUMBER_OF_GAME_AND_LIFE = "게임 횟수와 목숨을 입력하세요. (ex. 3, 1) 숫자와 ','외의 값은 무시됩니다.";
    private static final String INPUT_CORRECT_VALUE_NUMBER_OF_GAME_AND_LIFE = "게임 횟수와 목숨을 ','로 구분하여 차례대로 입력 해주세요.\n";
    private static final String INPUT_SINGLE_ALPHABET = "하나의 알파벳만 입력해 주세요.";

    // Suppresses default constructor, ensuring non-instantiability.
    private InputView() {}

    public static Hangman inputHangmanInfo() {
        String[] gameNumberAndLife;

        while (true) {
            String input = inputOf(NUMBER_OF_GAME_AND_LIFE);
            String filteredInput = input.replaceAll(REGEX_REMOVE_WITHOUT_NUMBER_AND_COMMA, BLANK);
            gameNumberAndLife = filteredInput.split(COMMA);

            if(gameNumberAndLife.length == 2) break;
            OutputView.printMessage(INPUT_CORRECT_VALUE_NUMBER_OF_GAME_AND_LIFE);
        }

        int numberGames = Integer.parseInt(gameNumberAndLife[INDEX_NUMBER_GAMES]);
        int life = Integer.parseInt(gameNumberAndLife[INDEX_LIFE]);

        try {
            return new Hangman(numberGames, life);
        } catch (IllegalArgumentException exception) {
            OutputView.printMessage(exception.getMessage());
            return inputHangmanInfo();
        }
    }

    public static char inputAlphabet(String roundInfo) throws IllegalArgumentException {
        String filteredInput;
        while (true) {
            String input = inputOf(roundInfo);
            filteredInput = input.replaceAll(REGEX_REMOVE_WITHOUT_ALPHABET, BLANK);

            if (filteredInput.length() == 1) break;
            OutputView.printMessage(INPUT_SINGLE_ALPHABET);
        }
        return filteredInput.toLowerCase().charAt(0);
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
