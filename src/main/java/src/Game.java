package src;

import src.result.GameResult;
import src.result.RoundResult;
import src.util.Message;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Game {

    private static Game game;
    private static List<GameDictionary> wordList = new ArrayList<>(Arrays.asList(GameDictionary.values()));
    public static List<GameResult> gameResults = new ArrayList<>();
    public static List<RoundResult> roundResults = new ArrayList<>();
    private static final Random RANDOM = new Random();

    public static Game getInstance() {
        if (game == null) {
            return new Game();
        }
        return game;
    }

    private Game() {
    }

    public static String getWordFromDict() {

        if (wordList.isEmpty()) {
            wordList = new ArrayList<>(Arrays.asList(GameDictionary.values()));
        }

        int idx = RANDOM.nextInt(wordList.size());
        GameDictionary dictionary = wordList.get(idx);
        wordList.remove(idx);
        return dictionary.getName();
    }

    public int[] initGame() {
        System.out.printf("게임 횟수와 목숨을 입력하세요 : ");
        return Input.getInitiationParam();
    }

    public void startGame(int numOfGame, int numOfLife) {

        int numOfPlayedGame = 0;

        while (numOfPlayedGame <= numOfGame) {

            GameResult gameResult = new GameResult();

            try {
                int numOfRound = 1;
                String wordOfGame = getWordFromDict();
                int lengthOfWord = wordOfGame.length();
                char[] answerArray = wordOfGame.toCharArray();

                System.out.printf("%d번째 게임이 시작됩니다. 정답 단어는 %d글자 입니다.\n", numOfPlayedGame + 1, lengthOfWord);

                char[] discoveredChars = new char[lengthOfWord];
                for (int j=0; j<lengthOfWord; j++) {
                    discoveredChars[j] = '_';
                }

                while (true) {

                    try {
                        boolean isCorrect = false;
                        String discoveredWord = new String(discoveredChars);
                        System.out.printf("%d 라운드 : " + discoveredWord + ", 목숨 %d\n", numOfRound, numOfLife);
                        System.out.printf("in : ");
                        char guessedChar = Input.getGameParam();
                        for (int i = 0; i < answerArray.length; i++) {
                            if (guessedChar == answerArray[i]) {
                                discoveredChars[i] = guessedChar;
                                isCorrect = true;
                            }
                        }

                        RoundResult roundResult = new RoundResult(numOfLife, new String(discoveredChars), guessedChar);
                        roundResults.add(roundResult);

                        gameResult.addRound(roundResult);

                        if (! isCorrect) numOfLife--;
                        if (numOfLife == 0) {
                            System.out.println("Game Over : 게임을 종료합니다.");

                            gameResult.setNumOfLife(numOfLife);
                            gameResult.setIsWin("실패");
                            gameResult.setAnswer(wordOfGame);
                            numOfPlayedGame++;

                            gameResults.add(gameResult);

                            printGameScore(gameResult);
                            Input.chooseMenu();
                        }

                        numOfRound++;

                        if (!new String(discoveredChars).contains("_")) {
                            System.out.println("축하합니다. 정답입니다.");

                            gameResult.setNumOfLife(numOfLife);
                            gameResult.setIsWin("성공");
                            gameResult.setAnswer(wordOfGame);
                            numOfPlayedGame++;

                            gameResults.add(gameResult);

                            printGameScore(gameResult);

                            if (numOfGame == 1) {
                                System.out.println("게임이 끝났습니다. 초기 메뉴화면을 출력합니다.");
                                Input.chooseMenu();
                            } else if (numOfPlayedGame < numOfGame) {
                                System.out.println("다음 게임을 시작합니다.");
                                break;
                            } else {
                                System.out.println("모든 게임이 끝났습니다. 초기 메뉴화면을 출력합니다.");
                                Input.chooseMenu();
                            }
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void printGameScore(GameResult gameResult) {
        System.out.println("=== Game Result ===");
        System.out.printf("게임 id : %d, 추측 : %s, 남은 목숨 : %d, 정답 : %s\n", gameResult.getGameId(), gameResult.getIsWin(), gameResult.getNumOfLife(), gameResult.getAnswer());
        System.out.println();
        for (RoundResult round : gameResult.getRounds()) {
            System.out.printf("라운드 id : %d, 남은 목숨 : %d, %s, 사용자 입력 : %c\n", round.getRoundId(), round.getNumOfLife(), round.getDiscoveredWord(), round.getStrFromUser());
        }
        System.out.println("==================");
    }

    public static void printRoundScore(RoundResult roundResult) {
        System.out.println("=== Round Result ===");
        System.out.printf("라운드 id : %d, 남은 목숨 : %d, %s, 사용자 입력 : %c\n", roundResult.getRoundId(), roundResult.getNumOfLife(), roundResult.getDiscoveredWord(), roundResult.getStrFromUser());
        System.out.println("==================");
    }

    public static void findGameById(int numOfGameId) {
        int idx = -1;

        for (int i=0; i<gameResults.size(); i++) {
            if (numOfGameId == gameResults.get(i).getGameId()) {
                idx = i;
                break;
            }
        }

        if (idx == -1) {
            throw new IllegalArgumentException(Message.ERR_NOT_VALID_PARAMETER);
        }

        printGameScore(gameResults.get(idx));
    }

    public static void findRoundById(int numOfRoundId) {
        int idx = -1;

        for (int i=0; i<roundResults.size(); i++) {
            if (numOfRoundId == roundResults.get(i).getRoundId()) {
                idx = i;
                break;
            }
        }

        if (idx == -1) {
            throw new IllegalArgumentException(Message.ERR_NOT_VALID_PARAMETER);
        }

        printRoundScore(roundResults.get(idx));
    }

}
