package src;

import src.result.GameResult;
import src.result.RoundResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Game {

    private static Game game;
    private static List<GameDictionary> wordList = new ArrayList<>(Arrays.asList(GameDictionary.values()));
    private  List<GameResult> gameResults = new ArrayList<>();

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

        int idx = new Random().nextInt(wordList.size());
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
        // 게임 루프
        while (numOfPlayedGame <= numOfGame) {
            GameResult gameResult = new GameResult();
            try {
                int numOfRound = 1;
                String wordOfGame = getWordFromDict();
                int lengthOfWord = wordOfGame.length();
                char[] answerArray = wordOfGame.toCharArray();

                System.out.printf("%d번째 게임이 시작됩니다. 정답 단어는 %d글자 입니다.\n", numOfPlayedGame + 1, lengthOfWord);

                char[] gameArray = new char[lengthOfWord];
                for (int j=0; j<lengthOfWord; j++) {
                    gameArray[j] = '_';
                }

                // 라운드 루프 제어 블럭
                while (true) {
                    RoundResult roundResult = new RoundResult();
                    try {
                        boolean isCorrect = false; // 라운드별 정답 여부를 체크할 boolean 변수
                        String discoveredWord = new String(gameArray);
                        System.out.printf("%d 라운드 : " + discoveredWord + ", 목숨 %d\n", numOfRound, numOfLife);
                        System.out.printf("in : ");
                        char input = Input.getGameParam();
                        for (int i = 0; i < answerArray.length; i++) {
                            if (input == answerArray[i]) { // 같은 거 찾으면
                                gameArray[i] = input;
                                isCorrect = true;
                            }
                        }
                        roundResult.setNumOfLife(numOfLife);
                        roundResult.setDiscoveredWord(new String(gameArray));
                        roundResult.setStrFromUser(input);
                        roundResult.setRoundId(RoundResult.ROUND_ID);
                        gameResult.addRound(roundResult);
                        System.out.println("test ROUND_ID : " + RoundResult.ROUND_ID);
                        gameResults.add(gameResult);

                        if (! isCorrect) numOfLife--; // 정답을 맞추지 못했다면 목숨을 차감한다
                        if (numOfLife == 0) {
                            System.out.println("Game Over : 게임을 종료합니다.");
                            break;
                        }

                        numOfRound++;

                        if (!new String(gameArray).contains("_")) {
                            System.out.println("축하합니다. 정답입니다.");
                            gameResult.setNumOfLife(numOfLife);
                            gameResult.setIsWin("성공");
                            gameResult.setAnswer(wordOfGame);
                            numOfPlayedGame++;

                            printGameScore(gameResult);

                            if (numOfGame == 1) {
                                System.out.println("게임끝");
                                System.exit(0);
                            } else if (numOfPlayedGame < numOfGame) {
                                System.out.println("다음 게임을 시작합니다.");
                                break;
                            } else { // 총 게임 횟수가 1번 아니고, 게임 플레이 횟수와 총 게임 횟수가 같아질 때
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

    public void printGameScore(GameResult gameResult) {
        System.out.println("=== Game Result ===");
        System.out.printf("게임 id : %d, 추측 : %s, 남은 목숨 : %d, 정답 : %s\n", gameResult.getGameId(), gameResult.getIsWin(), gameResult.getNumOfLife(), gameResult.getAnswer());
        System.out.println();
        for (RoundResult round : gameResult.getRounds()) {
            System.out.printf("라운드 id : %d, 남은 목숨 : %d, %s, 사용자 입력 : %c\n", round.getRoundId(), round.getNumOfLife(), round.getDiscoveredWord(), round.getStrFromUser());
        }
        System.out.println("==================");
    }

    public void getRoundScore() {

    }

}
