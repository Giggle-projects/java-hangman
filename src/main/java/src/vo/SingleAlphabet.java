package src.vo;

import java.util.HashMap;
import java.util.Map;

public class SingleAlphabet {

    private static final String ERR_INPUT_SINGLE_ALPHABET = "하나의 알파벳만 입력해 주세요.";
    private static final String REGEX_REMOVE_WITHOUT_ALPHABET = "[^a-zA-Z]";
    private static final String BLANK = "";

    private static final Map<Integer, SingleAlphabet> SINGLE_ALPHABETS = new HashMap<>();

    private final char alphabet;

    private SingleAlphabet(int code) {
        this.alphabet = (char) code;
    }

    public static SingleAlphabet of(String alphabet) throws IllegalArgumentException {
        int code = alphabetToASCIICode(alphabet);
        if (!SINGLE_ALPHABETS.containsKey(code)) {
            SINGLE_ALPHABETS.put(code, new SingleAlphabet(code));
        }
        return SINGLE_ALPHABETS.get(code);
    }

    private static int alphabetToASCIICode(String alphabet) throws IllegalArgumentException {
        String filteredAlphabet = filteringValue(alphabet);
        validateSingleCharacter(filteredAlphabet);
        return filteredAlphabet.toLowerCase().charAt(0);
    }

    private static void validateSingleCharacter(String alphabet) throws IllegalArgumentException {
        if (alphabet.length() != 1)
            throw new IllegalArgumentException(ERR_INPUT_SINGLE_ALPHABET);
    }

    private static String filteringValue(String alphabet) {
        return alphabet.replaceAll(REGEX_REMOVE_WITHOUT_ALPHABET, BLANK);
    }

    public char alphabet() {
        return alphabet;
    }
}
