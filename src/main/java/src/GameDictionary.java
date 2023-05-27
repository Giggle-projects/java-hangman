package src;

public enum GameDictionary {
    APPLE("NAME", 4),
    TEST("TEST", 4);

    private String name;
    private int length;

    GameDictionary(String name, int length) {
        this.name = name;
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }
}
