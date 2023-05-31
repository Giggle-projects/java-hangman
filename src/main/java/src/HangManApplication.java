package src;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class HangManApplication {

    public static void main(String[] args) {

        List<String> wordList = new ArrayList<>();
        wordList.add("apple");
        wordList.add("strawberry");
        wordList.add("grape");

        Random random = new Random();
        String word = wordList.get(random.nextInt(wordList.size()));

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] inputs = input.split(",");
        int numberGames = Integer.parseInt(inputs[0]);
        int numberLives = Integer.parseInt(inputs[1]);

    }
}
