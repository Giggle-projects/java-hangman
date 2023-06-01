package src;

import java.util.Random;

public class Set {
    private static String[] word = {"apple", "orange", "pineapple"};

    public String[] getWord() {
        return word;
    }

    public void setWord(String[] word) {
        this.word = word;
    }

    public static String setAnswer(){
        int idx = new Random().nextInt(word.length);
        return word[idx];
    }

    public static char[] setBlind(String str){
        char[] chars = new char[str.length()];
        for (int i = 0; i < str.length(); i++){
            chars[i] = '_';
        }
        return chars;
    }
}
