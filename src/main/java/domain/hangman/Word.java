package domain.hangman;

public enum Word {

    APPLE, BANANA, GRAPE;

    public int length() {
        return this.name().length();
    }
}
