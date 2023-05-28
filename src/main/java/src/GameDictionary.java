package src;

import java.util.Random;

public enum GameDictionary {
    APPLE("NAME", 4),
    TEST("TEST", 4),
    SENTENSE("SENTENSE", 8);

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

    public static String getWordFromDict() {
        GameDictionary[] words = GameDictionary.values();
        int idx = new Random().nextInt(words.length);
        return words[idx].getName();
    }


}
