package src;
import java.util.*;

public class HangManApplication {
    private static final Random RANDOM = new Random();
    private static final List<String> WORDLIST = new ArrayList<>(List.of("apple", "strawberry", "grape"));
    private static final List<GameInfo> gameInfoList = new ArrayList<>();
    private static final Map<Integer, RoundInfo> roundInfoMap = new HashMap<>();

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {
            while(true) {
                Menu.displayMenuOptions();
                try {
                    int inputNumber = scanner.nextInt();
                    Menu selectedMenu = Menu.getMenuByCode(inputNumber);
                    switch (selectedMenu) {
                        case GAME_START :
                            hangManGame(gameInfoList, roundInfoMap);
                            break;
                        case GAME_RESULT :
                            gameResult(scanner);
                            break;
                        case ROUND_RESULT :
                            roundResult(scanner);
                            break;
                        case EXIT :
                            System.out.println("프로그램이 종료 되었습니다.");
                            return;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("숫자를 입력해주세요 : ");
                    scanner.nextLine();
                } catch (IllegalArgumentException e) {
                    System.out.println("1 ~ 4의 숫자만 입력해주세요. ");
                }
            }
        } catch (IllegalStateException e) {
            e.getMessage();
        }
    }
    public static void hangManGame(List<GameInfo> gameInfoList,  Map<Integer, RoundInfo> roundInfoMap) {
        Scanner scanner = new Scanner(System.in);

        int numberGames = 0;
        int numberLives = 0;
        int inputNumberLength = 2;
        int negative = 0;

        while (true) {
            try {
                System.out.println("게임 횟수와 목숨을 입력하세요.");
                String input = scanner.nextLine();
                String[] inputNumbers = input.split(",");

                if (inputNumbers.length != inputNumberLength) {
                    throw new ArrayIndexOutOfBoundsException();
                }

                numberGames = Integer.parseInt(inputNumbers[0].trim());
                numberLives = Integer.parseInt(inputNumbers[1].trim());
                if (numberGames < negative || numberLives < negative) {
                    throw new IllegalArgumentException();
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("입력 형식에 맞게 ','로 구분하여 숫자로 입력해주세요. e");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("입력 형식에 맞게 ','로 구분하여 숫자로 입력해주세요.");
            } catch (IllegalArgumentException e) {
                System.out.println("음수는 입력할 수 없습니다.");
           }
        }

        int roundNumber = 1;
        int livesExhausted = 0;
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

            Set<Character> guessedChars = new LinkedHashSet<>();
            while (!isGameOver) {
                System.out.println(roundNumber + "라운드 : " + guess + ", 목숨 " + numberLives);
                    char inputGuess = scanner.next().charAt(0);
                    if (inputGuess < 'a' || inputGuess > 'z') {
                        System.out.println("알파벳만 입력해주세요.");
                        continue;
                    }
                    RoundInfo round = new RoundInfo(roundNumber++, numberLives, guess, inputGuess);
                    roundInfoMap.put(roundNumber, round);
                    roundList.add(round);
                    if (guessedChars.contains(inputGuess)) {
                        System.out.println("이미 입력한 알파벳입니다.");
                        roundNumber--;
                        continue;
                    }
                    guessedChars.add(inputGuess);

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
        if (gameId <= 0 || gameInfoList.size() < gameId) {
            System.out.println("아직 진행하지 않은 게임의 정보는 조회할 수 없습니다.");
            return;
        }
        int index = gameId - 1;
        gameInfoList.get(index).displayGameResult();
    }

    public static void roundResult(Scanner scanner) {
        System.out.println("라운드 id를 입력해주세요.");
        int roundId = scanner.nextInt();
        int index = roundId + 1;

        if (roundId < 0 || roundInfoMap.size() < roundId) {
            System.out.println("아직 진행하지 않은 라운드의 정보는 조회할 수 없습니다.");
            return;
        }

        System.out.println("=== Round Result ===");
        if(roundInfoMap.containsKey(roundId)) {
            System.out.println(roundInfoMap.get(index));
        }
        System.out.println("===================");
    }
}
