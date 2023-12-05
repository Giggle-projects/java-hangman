package view;

import domain.hangman.Life;
import domain.hangman.Round;
import util.Console;

import java.util.function.Supplier;

public class InputView {

    private static final String OUTPUT_PREFIX = "OU : ";
    private static final String INPUT_PREFIX = "IN : ";

    private static final String INPUT_FORMAT = OUTPUT_PREFIX + "%S" + "\n" + INPUT_PREFIX;

    private static <T> T repeatUntilNoException(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    public static Round inputRound() {
        return repeatUntilNoException(() -> {
            System.out.printf(INPUT_FORMAT, "게임 횟수를 입력해주세요.");
            int round = readToInt(Console.readLine());

            return new Round(round);
        });
    }

    private static int readToInt(String read) {
        return repeatUntilNoException(() -> {
            try {
                return Integer.parseInt(read);
            } catch (NumberFormatException exception) {
                throw new IllegalArgumentException(OUTPUT_PREFIX + "숫자를 입력해주세요.");
            }
        });
    }

    public static Life inputLife() {
        return repeatUntilNoException(() -> {
            System.out.printf(INPUT_FORMAT, "게임 목숨을 입력해주세요.");
            int life = readToInt(Console.readLine());

            return new Life(life);
        });
    }
}
