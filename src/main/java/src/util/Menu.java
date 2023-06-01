package src.util;

public enum Menu {
    PLAY_GAME("게임하기"),
    SHOW_GAME_RESULT("게임 결과 보기"),
    SHOW_ROUND_RESULT("라운드 결과 보기"),
    END("종료");
    
    private final String name;

    Menu(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isEnd() {
        return this == Menu.END;
    }

}
