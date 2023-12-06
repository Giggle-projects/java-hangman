package domain.hangman;

import java.util.ArrayList;
import java.util.List;

public class Alphabet {

    private final String alphabet;

    public Alphabet(String alphabet) {
        this.validate(alphabet);
        this.alphabet = alphabet.toUpperCase();
    }

    private void validate(String alphabet) {
        this.validateIsNotEmpty(alphabet);
        this.validateIsNotBlank(alphabet);
        this.validateIsSingleAlphabet(alphabet);
        this.validateIsAlphabetic(alphabet);
    }

    private void validateIsNotEmpty(String alphabet) {
        if (alphabet.isEmpty()) {
            throw new IllegalArgumentException("입력 알파벳은 비어있을 수 없음.");
        }
    }

    private void validateIsNotBlank(String alphabet) {
        if (alphabet.isBlank()) {
            throw new IllegalArgumentException("입력 알파벳은 공백일 수 없음.");
        }
    }

    private void validateIsSingleAlphabet(String alphabet) {
        if (alphabet.length() > 1) {
            throw new IllegalArgumentException("하나의 알파벳만 입력해야 함.");
        }
    }

    private void validateIsAlphabetic(String alphabet) {
        if (!Character.isAlphabetic(alphabet.charAt(0))) {
            throw new IllegalArgumentException("알파벳을 입력해야 함.");
        }
    }

    public List<Integer> extractMatchIndex(String word) {
        if (!word.contains(alphabet)) {
            return List.of();
        }

        List<Integer> hitIndex = new ArrayList<>();
        String[] words = word.split("");
        for (int i = 0; i < word.length(); i++) {
               if (words[i].equals(alphabet)) {
                   hitIndex.add(i);
               }
        }
        return hitIndex;
    }
}
