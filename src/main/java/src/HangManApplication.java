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

        Scanner scanner = new Scanner(System.in);
        System.out.println("게임 횟수와 목숨을 입력하세요.");
        String input = scanner.nextLine();
        String[] inputs = input.split(",");
        int numberGames = Integer.parseInt(inputs[0]);
        int numberLives = Integer.parseInt(inputs[1]);

        for (int i = 1; i <= numberGames; i++) {

            String word = wordList.get(random.nextInt(wordList.size()));
            System.out.println((i) + "번째 게임이 시작됩니다. 정답 단어는 " + word.length() + "글자 입니다.");

            char[] guessedWord = new char[word.length()];
            for (int j = 0; j < word.length(); j++) {
                guessedWord[j] = '_';
            }
            String guess = new String(guessedWord);

            boolean isSaved;
            int roundNumber = 1;
            for (int j = numberLives; j > 0; j--) {
                System.out.println(roundNumber + "라운드 : " + guess + ", 목숨 " + numberLives);
                char inputGuess = scanner.next().charAt(0);

                isSaved = false;
                for (int k = 0; k < word.length(); k++) {
                    if (word.charAt(k) == inputGuess) {
                        guessedWord[k] = inputGuess;
                        isSaved = true;
                    }
                }

                if (!isSaved) {
                    numberLives--;
                    if (numberLives == 0) {
                        System.out.println("목숨이 소진 되었습니다.");
                        break;
                    }
                }
                j++;
                roundNumber++;

                guess = new String(guessedWord);
                if (guess.equals(word)) {
                    System.out.println(roundNumber + "라운드 : " + guess + ", 목숨 " + numberLives);
                    System.out.println("축하합니다. 정답입니다.");
                    break;
                }
            }
            if (i < numberGames && numberLives > 0) {
                System.out.println("다음 게임을 시작합니다.");
            }
        }

    }
}
