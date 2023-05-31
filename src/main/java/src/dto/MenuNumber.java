package src.dto;

import java.util.List;

public class MenuNumber {

    private static final String ERR_INPUT_RANGE_OF_MENU = "메뉴 범위의 숫자를 입력해 주세요.";
    private static final int MIN_MENU_NUMBER = 1;
    private static final int MAX_MENU_NUMBER = 3;

    private static final List<MenuNumber> MENU_NUMBERS;

    static {
        MENU_NUMBERS = List.of(new MenuNumber(1), new MenuNumber(2), new MenuNumber(3));
    }

    private int number;

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
        return MENU_NUMBERS.get(menuNumber-1);
    }

    public int number() {
        return number;
    }
}
