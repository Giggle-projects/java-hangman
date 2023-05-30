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
            HangmanGameRoundTable.HangmanGameRoundInfo round = roundTable.getRound(roundId);
            result.append(String.format(ROUND_PRINT_FORMAT
                    , roundId, round.remainingLife, round.correctingWord, round.inputAlphabet));
        }
        printMessage(result.append("===================\n").toString());
    }

    public static void printRoundResult() {

    }
}
