package src;

public enum GameDictionary {

    APPLE("NAME"),
    TEST("TEST"),
    SENTENSE("SENTENSE");

    private String name;
    private int length;

    GameDictionary(String name) {
        this.name = name;
        this.length = name.length();
    }

    public String getName() {
        return name;
    }
}
