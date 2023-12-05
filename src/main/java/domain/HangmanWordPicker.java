package domain;

import java.util.Objects;
import java.util.Random;

public class HangmanWordPicker implements RandomWordPicker {

    private static HangmanWordPicker hangmanWordPicker;

    private HangmanWordPicker() {

    }

    public static HangmanWordPicker getInstance() {
        if (Objects.isNull(hangmanWordPicker)) {
            hangmanWordPicker = new HangmanWordPicker();
        }
        return hangmanWordPicker;
    }

    @Override
    public Word pick() {
        Word[] words = Word.values();
        Random random = new Random();
        int randomIndex = random.nextInt(words.length);

        return words[randomIndex];
    }
}
