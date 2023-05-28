package src;

public class Game {
    private static Game game;


    public static Game getInstance() {
        if (game == null) {
            return new Game();
        }
        return game;
    }

    private Game() {

    }

    public int[] initGame() {
        System.out.printf("게임 횟수와 목숨을 입력하세요 : ");
        return Input.getInitiationParam();
    }

    public void startGame(int numOfGame, int numOfLife) {
        int numOfPlayedGame = 1;
        while (numOfPlayedGame <= numOfGame) {
            try {
                int numOfRound = 1;
                String word = GameDictionary.getWordFromDict();
                int lengthOfWord = word.length();
                char[] answerArray = word.toCharArray();

                if (numOfPlayedGame == numOfGame) break;

                System.out.printf("%d번째 게임이 시작됩니다. 정답 단어는 %d글자 입니다.\n", numOfPlayedGame, lengthOfWord);

                char[] gameArray = new char[lengthOfWord];
                for (int j=0; j<lengthOfWord; j++) {
                    gameArray[j] = '_';
                }

                while (true) {
                    boolean isCorrect = false;
                    //char[] gameArray = new char[lengthOfWord];
//                    for (int j=0; j<lengthOfWord; j++) {
//                        gameArray[j] = '_';
//                    }
                    String discoveredWord = new String(gameArray);
                    System.out.printf("%d 라운드 : " + discoveredWord + ", 목숨 %d\n", numOfRound, numOfLife);
                    System.out.printf("in : ");
                    char input = Input.getGameParam();
                    for (int i=0; i<answerArray.length; i++) {
                        if (input == answerArray[i]) { // 같은 거 찾으면
                            gameArray[i] = input;
                            System.out.println("게임 어레이 확인 : " + gameArray[i]);
                            isCorrect = true;
                        }
                    }
                    System.out.println("isCorrect 확인 : " + isCorrect);
                    if (! isCorrect) numOfLife--; // 정답을 맞추지 못했다면 목숨을 차감한다
                    if (numOfLife == 0) break; // 목숨이 0이 되는 경우 break
                    if (! new String(gameArray).contains("_")) {
                        if (numOfPlayedGame < numOfGame) {
                            System.out.println("다음 게임을 시작합니다.");
                            numOfPlayedGame++;
                            break;
                        } else if (numOfPlayedGame == numOfGame) {
                            getScore();
                        }

                        // 정답을 다 맞춰서 배열에 _가 남아있지 않은 경우 break
                    }
                    numOfRound++;
                }
                numOfPlayedGame++;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void getScore() {
        System.out.println("게임끝, 종료");
        System.exit(0);
    }

}
