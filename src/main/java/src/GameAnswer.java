package src;

import java.util.Random;

public class GameAnswer {
    private static final String[] word = {"apple", "orange", "pineapple"};

    public static String setAnswer(){
        int idx = new Random().nextInt(word.length);
        return word[idx];
    }

    public static char[] hideAnswer(String str){
        char[] answer = new char[str.length()];
        for (int i = 0; i < str.length(); i++){
            answer[i] = '_';
        }
        return answer;
    }
}
