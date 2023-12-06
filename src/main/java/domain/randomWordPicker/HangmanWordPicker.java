package domain.randomWordPicker;

import domain.hangman.Word;

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
        RandomWord[] words = RandomWord.values();
        Random random = new Random();
        int randomIndex = random.nextInt(words.length);

        String word = words[randomIndex].name();
        return new Word(word);
    }
}
