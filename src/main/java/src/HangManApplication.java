package src;
import java.util.*;

public class HangManApplication {
    private static final Random RANDOM = new Random();
    private static final List<String> WORDLIST = new ArrayList<>();
    private static final List<GameInfo> gameInfoList = new ArrayList<>();
    private static final Map<Integer, RoundInfo> roundInfoMap = new HashMap<>();

    public static void main(String[] args) {

        WORDLIST.add("apple");
        WORDLIST.add("strawberry");
        WORDLIST.add("grape");

        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("메뉴를 선택합니다. (1 : 게임하기, 2 : 게임 결과 보기, 3 : 라운드 결과 보기)");

            try {
                int inputNumber = scanner.nextInt();
                switch (inputNumber) {
                    case 1 :
                        hangManGame(gameInfoList, roundInfoMap);
                        break;
                    case 2 :
                        gameResult(scanner);
                        break;
                    case 3 :
                        roundResult(scanner);
                        break;
                    default :
                        System.out.println("프로그램이 종료 되었습니다.");
                        return;
                }
            } catch (InputMismatchException e) {
                System.out.println("숫자를 입력해주세요 : ");
                scanner.nextLine();
            }
        }
    }
    public static void hangManGame(List<GameInfo> gameInfoList,  Map<Integer, RoundInfo> roundInfoMap) {
        Scanner scanner = new Scanner(System.in);

        int numberGames = 0;
        int numberLives = 0;
        int inputNumberLength = 2;
        int livesExhausted = 0;

        while (true) {
            try {
                System.out.println("게임 횟수와 목숨을 입력하세요.");
                String input = scanner.nextLine();
                String[] inputNumbers = input.split(",");
                if (inputNumbers.length != inputNumberLength) {
                    throw new IllegalArgumentException("게임 횟수와 목숨 횟수만 입력하세요.");
                }
                numberGames = Integer.parseInt(inputNumbers[0].trim());
                numberLives = Integer.parseInt(inputNumbers[1].trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("입력 형식에 맞게 ','로 구분하여 숫자로 입력해주세요.");
            } catch (IllegalArgumentException e) {
                System.out.println("입력 형식에 맞게 ','로 구분하여 숫자로 입력해주세요.");
            }
        }

        int roundNumber = 1;
        int initialNumberLives = numberLives;
        for (int game = 1; game <= numberGames; game++) {
            List<RoundInfo> roundList = new ArrayList<>();
            String word = WORDLIST.get(RANDOM.nextInt(WORDLIST.size()));
            System.out.println((game) + "번째 게임이 시작됩니다. 정답 단어는 " + word.length() + "글자 입니다.");

            char[] guessedWord = new char[word.length()];
            for (int j = 0; j < word.length(); j++) {
                guessedWord[j] = '_';
            }
            String guess = new String(guessedWord);

            boolean isGameOver = false;
            boolean isSaved = false;

            while (!isGameOver) {
                System.out.println(roundNumber + "라운드 : " + guess + ", 목숨 " + numberLives);
                char inputGuess = scanner.next().charAt(0);
                RoundInfo round = new RoundInfo(roundNumber++, numberLives, guess, inputGuess);
                roundInfoMap.put(roundNumber, round);
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
                    if (numberLives == livesExhausted) {
                        System.out.println("정답 맞추기에 실패하였습니다.");
                        break;
                    }
                }

                guess = new String(guessedWord);
                if (guess.equals(word)) {
                    System.out.println(roundNumber + "라운드 : " + guess + ", 목숨 " + numberLives);
                    System.out.println("축하합니다. 정답입니다.");
                    break;
                }
            }

            if (game < numberGames && numberLives > livesExhausted) {
                System.out.println("다음 게임을 시작합니다.");
            }

            GameInfo gameInfo = new GameInfo(game, word, isSaved, numberLives, roundList);
            gameInfoList.add(gameInfo);
            numberLives = initialNumberLives;
        }
    }
    public static void gameResult(Scanner scanner) {
        System.out.println("게임 id를 입력해주세요.");
        int gameId = scanner.nextInt();
        int index = --gameId;
        int numOfElements = 0;
        if (gameInfoList.size() == numOfElements || gameInfoList.size() < gameId) {
            System.out.println("아직 진행하지 않은 게임의 정보는 조회할 수 없습니다.");
            return;
        }
        gameInfoList.get(index).displayGameResult();
    }

    public static void roundResult(Scanner scanner) {
        System.out.println("라운드 id를 입력해주세요.");
        int roundId = scanner.nextInt();
        int index = 1;
        System.out.println("=== Round Result ===");
        Iterator<Integer> keys = roundInfoMap.keySet().iterator();
        while(keys.hasNext()){
            Integer key = keys.next();
            if (key == roundId) {
                System.out.println(roundInfoMap.get(key+index));
            }
        }
        System.out.println("===================");
    }
}
