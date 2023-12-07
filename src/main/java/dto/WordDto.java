package dto;

public class WordDto {

    private final String word;
    private final boolean[] isMatched;

    public WordDto(String word, boolean[] isMatched) {
        this.word = word;
        this.isMatched = isMatched;
    }

    public String getHint(String hiddenWord) {
        StringBuilder hint = new StringBuilder();
        String[] wordSpelling = word.split("");

        for (int i = 0; i < word.length(); i++) {
            String currentChar = hiddenWord;
            if (isMatched[i]) {
                currentChar = wordSpelling[i];
            }
            hint.append(currentChar);
        }
        return hint.toString();
    }
}
