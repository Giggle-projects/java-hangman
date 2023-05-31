package src.dto;

public class MenuNumber {

    private static final String ERR_INPUT_RANGE_OF_MENU = "메뉴 범위의 숫자를 입력해 주세요.";
    private static final int MIN_MENU_NUMBER = 1;
    private static final int MAX_MENU_NUMBER = 3;

    private int number;

    public MenuNumber(int MenuNumber) throws IllegalArgumentException {
        validateRangeOfMenuNumber(MenuNumber);
        this.number = MenuNumber;
    }

    private void validateRangeOfMenuNumber(int MenuNumber) throws IllegalArgumentException {
        if (MIN_MENU_NUMBER > MenuNumber || MenuNumber > MAX_MENU_NUMBER){
            throw new IllegalArgumentException(ERR_INPUT_RANGE_OF_MENU);
        }
    }

    public int number() {
        return number;
    }
}
