package src.view;

import src.hangmanGame.HangmanGameRoundTable;

import java.util.Iterator;

public class OutputView {

    private static final String OU = "ou : ";

    private static final String ROUND_PRINT_FORMAT = "라운드 id: %d, 남은 목숨 : %d, %s, 사용자 입력 : %c\n";


    // Suppresses default constructor, ensuring non-instantiability.
    private OutputView() {}

    public static void printMessage(String message) {
        System.out.println(OU + message);
    }

    public static void printGameResult(HangmanGameRoundTable roundTable) {
        StringBuilder result = new StringBuilder();
        result.append("\n=== Game Result ===\n").append(roundTable.gameResult());
        for (Integer roundId : roundTable) {
            HangmanGameRoundTable.HangmanGameRoundInfo roundInfo = roundTable.getRound(roundId);
            result.append(roundResultToString(roundInfo));
        }
        printMessage(result.append("===================\n").toString());
    }

    public static void printRoundResult(HangmanGameRoundTable.HangmanGameRoundInfo roundInfo) {
        StringBuilder result = new StringBuilder();
        result.append("\n=== Round Result ===\n")
                .append(roundResultToString(roundInfo))
                .append("===================\n");
        printMessage(result.toString());
    }

    private static String roundResultToString(HangmanGameRoundTable.HangmanGameRoundInfo roundInfo) {
        return String.format(ROUND_PRINT_FORMAT
                , roundInfo.roundId
                , roundInfo.remainingLife
                , roundInfo.correctingWord
                , roundInfo.inputAlphabet);
    }
}
