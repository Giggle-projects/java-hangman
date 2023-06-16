package src.userManager;

public class User {
    private String name;  // 사용자 이름 추가
    private Integer life;
    private Integer gameNum;

    public User(String name, Integer life, Integer gameNum) {
        this.name = name;
        this.life = life;
        this.gameNum = gameNum;
    }

    public String getName() {
        return name;
    }

    public Integer getLife() {
        return life;
    }

    public Integer getGameNum() {
        return gameNum;
    }

}
