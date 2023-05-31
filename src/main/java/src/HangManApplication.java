package src;

import java.util.*;


public class HangManApplication {

    private static final Random random = new Random();
    private static final List<String> wordList = new ArrayList<>();

    public static void main(String[] args) {

        List<GameInfo> gameInfoList = new ArrayList<>();

        wordList.add("apple");
        wordList.add("strawberry");
        wordList.add("grape");

        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("메뉴를 선택합니다. (1 : 게임하기, 2 : 게임 결과 보기, 3 : 라운드 결과 보기)");
            int inputNumber = scanner.nextInt();
            if (inputNumber == 1) {
                game(gameInfoList);
            }
        }
    }
    public static void game(List<GameInfo> gameInfoList) {
        Scanner scanner = new Scanner(System.in);

        int numberGames = 0;
        int numberLives = 0;

        while (true) {
            try {
                System.out.println("게임 횟수와 목숨을 입력하세요.");
                String input = scanner.nextLine();
                String[] inputs = input.split(",");
                if (inputs.length != 2) {
                    throw new IllegalArgumentException("게임 횟수와 목숨 횟수만 입력하세요.");
                }
                numberGames = Integer.parseInt(inputs[0].trim());
                numberLives = Integer.parseInt(inputs[1].trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("','로 구분하여 숫자로 입력해주세요.");
            }
        }

        int roundNumber = 1;
        for (int i = 1; i <= numberGames; i++) {
            List<Round> roundList = new ArrayList<>();

            String word = wordList.get(random.nextInt(wordList.size()));
            System.out.println((i) + "번째 게임이 시작됩니다. 정답 단어는 " + word.length() + "글자 입니다.");

            char[] guessedWord = new char[word.length()];
            for (int j = 0; j < word.length(); j++) {
                guessedWord[j] = '_';
            }
            String guess = new String(guessedWord);

            boolean isSaved = false;
            for (int j = numberLives; j > 0; j--) {
                System.out.println(roundNumber + "라운드 : " + guess + ", 목숨 " + numberLives);
                char inputGuess = scanner.next().charAt(0);
                Round round = new Round(roundNumber++, numberLives, guess, inputGuess);
                roundList.add(round);

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

            GameInfo gameInfo = new GameInfo(i, word, isSaved, numberLives, roundList);
            gameInfoList.add(gameInfo);
        }
    }
}
