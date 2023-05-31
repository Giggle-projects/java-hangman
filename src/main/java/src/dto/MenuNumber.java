package src.dto;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class MenuNumber {

    private static final String ERR_INPUT_RANGE_OF_MENU = "메뉴 범위의 숫자를 입력해 주세요.";
    private static final int MIN_MENU_NUMBER = 1;
    private static final int MAX_MENU_NUMBER = 3;

    private static final Map<Integer, MenuNumber> MENU_NUMBERS;

    static {
        MENU_NUMBERS = new HashMap<>();
        IntStream.range(MIN_MENU_NUMBER, MAX_MENU_NUMBER)
                .forEach(i -> MENU_NUMBERS.put(i, new MenuNumber(i)));
    }

    private final int number;

    private MenuNumber(int MenuNumber) {
        this.number = MenuNumber;
    }

    private static void validateRangeOfMenuNumber(int MenuNumber) throws IllegalArgumentException {
        if (MIN_MENU_NUMBER > MenuNumber || MenuNumber > MAX_MENU_NUMBER){
            throw new IllegalArgumentException(ERR_INPUT_RANGE_OF_MENU);
        }
    }

    public static MenuNumber of(int menuNumber) {
        validateRangeOfMenuNumber(menuNumber);
        return MENU_NUMBERS.get(menuNumber);
    }

    public int number() {
        return number;
    }
}
