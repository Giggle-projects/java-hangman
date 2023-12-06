package dto;

public class WordDto {

    private final String word;
    private final boolean[] matchIndex;

    public WordDto(String word, boolean[] matchIndex) {
        this.word = word;
        this.matchIndex = matchIndex;
    }

    public String matchedWord(String mismatchWord) {
        StringBuilder matchedWord = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            String one = mismatchWord;
            if (matchIndex[i]) {
                one = word.split("")[i];
            }
            matchedWord.append(one);
        }
        return matchedWord.toString();
    }
}
