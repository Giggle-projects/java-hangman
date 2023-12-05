package domain;

public enum Word {

    APPLE, BANANA, GRAPE;

    public int length() {
        return this.name().length();
    }
}
