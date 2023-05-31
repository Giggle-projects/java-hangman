package src.dto;

public class SingleAlphabet {

    private static final String ERR_INPUT_SINGLE_ALPHABET = "하나의 알파벳만 입력해 주세요.";
    private static final String REGEX_REMOVE_WITHOUT_ALPHABET = "[^a-zA-Z]";
    private static final String BLANK = "";

    private final char alphabet;

    public SingleAlphabet(String alphabet) throws IllegalArgumentException {
        String filteredValue = filteringValue(alphabet);
        validateSingleCharacter(filteredValue);
        this.alphabet = alphabet.toLowerCase().charAt(0);
    }

    public char alphabet() {
        return alphabet;
    }

    private void validateSingleCharacter(String alphabet) throws IllegalArgumentException {
        if (alphabet.length() != 1)
            throw new IllegalArgumentException(ERR_INPUT_SINGLE_ALPHABET);
    }

    private String filteringValue(String alphabet) {
        return alphabet.replaceAll(REGEX_REMOVE_WITHOUT_ALPHABET, BLANK);
    }
}
