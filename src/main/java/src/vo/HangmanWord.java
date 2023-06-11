package src.vo;

public enum HangmanWord {

    APPLE("apple"),
    COMPUTER("computer"),
    JAVA("java"),
    HYUNSOO("hyunsoo");

    private final String spelling;

    HangmanWord(String word) {
        this.spelling = word;
    }

    public String getSpelling() {
        return spelling;
    }
}
