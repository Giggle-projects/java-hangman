package domain.hangman;

import dto.WordDto;

import java.util.List;

public class Word {

    private final String word;
    private boolean[] matchIndex;

    public Word(String word) {
        this.word = word.toUpperCase();
        matchIndex = new boolean[word.length()];
    }

    public int length() {
        return word.length();
    }

    public boolean tryToMatch(Alphabet alphabet) {
        List<Integer> matchIndex = alphabet.extractMatchIndex(word);
        if (matchIndex.isEmpty()) {
            return false;
        }
        this.fillMatchIndex(matchIndex);
        return true;
    }

    private void fillMatchIndex(List<Integer> matchIndex) {
        for (Integer index : matchIndex) {
            this.matchIndex[index] = true;
        }
    }

    public boolean isAllMatched() {
        for (boolean index : matchIndex) {
            if (!index) {
                return false;
            }
        }
        return true;
    }

    public WordDto toDto() {
        return new WordDto(word, matchIndex);
    }
}
