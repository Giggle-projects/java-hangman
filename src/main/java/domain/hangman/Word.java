package domain.hangman;

import dto.WordDto;

import java.util.List;

public class Word {

    private final String word;
    private final boolean[] isMatched;

    public Word(String word) {
        this.word = word.toUpperCase();
        isMatched = new boolean[word.length()];
    }

    public int length() {
        return word.length();
    }

    public boolean tryToMatch(Alphabet alphabet) {
        List<Integer> matchedIndex = alphabet.extractMatchedIndex(word);
        if (matchedIndex.isEmpty()) {
            return false;
        }
        this.fillIsMatched(matchedIndex);
        return true;
    }

    private void fillIsMatched(List<Integer> matchedIndex) {
        for (Integer index : matchedIndex) {
            isMatched[index] = true;
        }
    }

    public boolean isAllMatched() {
        for (boolean index : isMatched) {
            if (!index) {
                return false;
            }
        }
        return true;
    }

    public WordDto toDto() {
        return new WordDto(word, isMatched);
    }
}
