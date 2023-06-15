package src;

enum Menu {
    GAME_START(1, "게임하기"), GAME_RESULT(2, "게임 결과보기"),
    ROUND_RESULT(3, "라운드 결과보기"),
    EXIT(4, "종료");

    private int code;
    private String description;

    Menu(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static Menu getMenuByCode(int code) {
        Menu[] menuArr = Menu.values();
        for (int i = 0; i < menuArr.length; i++) {
            if (menuArr[i].getCode() == code) {
                return menuArr[i];
            }
        }
        throw new IllegalArgumentException();
    }

    public static void displayMenuOptions() {
        Menu[] menus = Menu.values();
        int menuOptionLength  = menus.length - 1;
        System.out.print("메뉴를 선택합니다. (");
        for (int index = 0; index < menus.length; index++) {
            System.out.print(menus[index].getCode() + " : " + menus[index].getDescription());
            if (index < menuOptionLength) {
                System.out.print(", ");
            }
        }
        System.out.println(")");
    }
}