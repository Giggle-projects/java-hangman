package src.view;

import src.dto.MenuNumber;
import src.dto.SingleAlphabet;
import src.hangmanGame.Hangman;
import src.util.Console;

public class InputView {

    private static final String OU = "ou : ";
    private static final String IN = "in : ";

    private static final String BLANK = "";
    private static final String COMMA = ",";

    private static final String REGEX_REMOVE_WITHOUT_NUMBER_AND_COMMA = "[^0-9,]";

    private static final int INDEX_NUMBER_GAMES = 0;
    private static final int INDEX_LIFE = 1;

    private static final String CHOICE_GAME_MENU = "메뉴를 선택합니다. (1 : 게임하기, 2 : 게임 결과 보기, 3 : 라운드 결과 보기)";
    private static final String NUMBER_OF_GAME_AND_LIFE = "게임 횟수와 목숨을 입력하세요. (ex. 3, 1) 숫자와 ','외의 값은 무시됩니다.";

    private static final String ERR_INPUT_CORRECT_VALUE_NUMBER_OF_GAME_AND_LIFE = "게임 횟수와 목숨을 ','로 구분하여 차례대로 입력 해주세요.\n";
    private static final String ERR_INPUT_NUMBER = "숫자를 입력해 주세요.";

    // Suppresses default constructor, ensuring non-instantiability.
    private InputView() {}

    public static Hangman.HangmanInfo inputHangmanInfo() {
        String[] gameNumberAndLife;

        while (true) {
            String input = inputOf(NUMBER_OF_GAME_AND_LIFE);
            String filteredInput = input.replaceAll(REGEX_REMOVE_WITHOUT_NUMBER_AND_COMMA, BLANK);
            gameNumberAndLife = filteredInput.split(COMMA);

            if(gameNumberAndLife.length == 2) break;
            OutputView.printMessage(ERR_INPUT_CORRECT_VALUE_NUMBER_OF_GAME_AND_LIFE);
        }

        int numberGames = Integer.parseInt(gameNumberAndLife[INDEX_NUMBER_GAMES]);
        int life = Integer.parseInt(gameNumberAndLife[INDEX_LIFE]);

        try {
            return new Hangman.HangmanInfo(numberGames, life);
        } catch (IllegalArgumentException exception) {
            OutputView.printMessage(exception.getMessage());
            return inputHangmanInfo();
        }
    }

    public static SingleAlphabet inputSingleAlphabet(String roundInfo) {
        try {
            String input = inputOf(roundInfo);
            return SingleAlphabet.of(input);
        } catch (IllegalArgumentException exception) {
            OutputView.printMessage(exception.getMessage());
            return inputSingleAlphabet(roundInfo);
        }
    }

    public static MenuNumber inputMenuNumber() {
        try {
            int input = inputNumberOf(CHOICE_GAME_MENU);
            return MenuNumber.of(input);
        } catch (IllegalArgumentException exception) {
            OutputView.printMessage(exception.getMessage());
            return inputMenuNumber();
        }
    }

    public static int inputNumberOf(String message) {
        String input = inputOf(message);
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            OutputView.printMessage(ERR_INPUT_NUMBER);
            return inputNumberOf(message);
        }
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
