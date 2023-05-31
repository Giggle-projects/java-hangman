package src.dto;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class SingleAlphabet {

    private static final String ERR_INPUT_SINGLE_ALPHABET = "하나의 알파벳만 입력해 주세요.";
    private static final String REGEX_REMOVE_WITHOUT_ALPHABET = "[^a-zA-Z]";
    private static final String BLANK = "";

    private static final int START_ALPHABET_ASCII_DECIMAL = 97;
    private static final int END_ALPHABET_ASCII_DECIMAL = 122;

    private static final Map<Integer, SingleAlphabet> SINGLE_ALPHABETS;

    static {
        SINGLE_ALPHABETS = new HashMap<>();
        IntStream.range(START_ALPHABET_ASCII_DECIMAL, END_ALPHABET_ASCII_DECIMAL)
                .forEach(i -> SINGLE_ALPHABETS.put(i, new SingleAlphabet(i)));
    }

    private final char alphabet;

    private SingleAlphabet(int code) throws IllegalArgumentException {
        this.alphabet = (char) code;
    }

    public static SingleAlphabet of(String alphabet) throws IllegalArgumentException {
        int code = alphabetToASCIICode(alphabet);
        return SINGLE_ALPHABETS.get(code);
    }

    public char alphabet() {
        return alphabet;
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
}
