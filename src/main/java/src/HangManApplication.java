package src;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
 
public class HangManApplication {
    private static final String[] WORDS = {"apple", "elephant", "galaxy", "adventure", "sexy"};
    
    private int maxGameId;
    private int maxLives;
    private int gameId;
    private int lives;
    private String answer;
    private char[] guessedWord;
    private boolean gameSuccess;

    private HashMap<Integer, String[]> logHistory = new HashMap<>();
    private HashMap<Integer, String> gameResult = new HashMap<>();
    private HashSet<Character> guessHistory = new HashSet<>();
    private String[] log;
    
    Scanner scanner = new Scanner(System.in);

    public void start() {
        // Set number of games and number of lives.
        initializeSettings();
        
        for (int i = 1; i <= maxGameId; i++) {
            gameId = i;

            // Initialize before start game.
            initializeGame();
            
            // Play game
            playGame();
            
            // Write game result.
            writeGameResult();

            // Write round log.
            writeLogHistory();

            // Print game result.
            printGameResult();
        }
    }

    private void initializeSettings(){
        System.out.print("게임 횟수를 입력하세요 : ");
        this.maxGameId = scanner.nextInt();

        System.out.print("목숨을 입력하세요 : ");
        this.maxLives = scanner.nextInt();
    
    }

    private void initializeGame() {
        this.gameSuccess = false;

        // Make a set to avoid selecting duplicate char.
        this.guessHistory = new HashSet<>();

        // Get random word.
        this.answer = WORDS[(int)(Math.random()*WORDS.length)];
        
        // Setting lives before start game.
        this.lives = maxLives;

        // Initialize log.
        this.log = new String[answer.length() + maxLives];

        // Initialize guessword.
        this.guessedWord = new char[answer.length()];
        for (int i = 0; i < answer.length(); i++) {
            guessedWord[i] = '_';
        }

        // Print game information.
        printGameInfo();
    }

    // STEP 1 : Play game.
    private void playGame() {
        int roundId = 1;

        while (lives > 0) {
            // Print round information.
            printGameRound();

            // Get user typed char 
            char guess = getUserGuess();

            // Check success
            boolean guessSuccess = checkGuessSuccess(guess);

            if (gameSuccess){
                break;
            }else if (guessSuccess != true){
                lives--;
            }
            
            // Write log
            writeLog(roundId, guess);

            roundId++;
        }
    }
    
    // Compare user guessed word with answer.
    private boolean checkGuessSuccess(char guess) {
        boolean guessSuccess = false;
        gameSuccess = true;

        for (int i=0; i<answer.length(); i++){
            // Is guessed char in answer. 
            if (guess == answer.charAt(i)){
                guessedWord[i] = guess; 
                guessSuccess = true;
            }

            // Check game success.
            if (guessedWord[i] == '_'){
                gameSuccess = false;
            }
        }
        return guessSuccess;
    }

    // STEP 2 : Check correct input form.
    private char getUserGuess() {
        char returnChar;
        System.out.print("문자를 입력하세요: ");

        while (true){
            try {
                String userInput = scanner.next();
                
                // Check length.
                if (userInput.length() == 1) {
                    char inputChar = userInput.charAt(0);
                
                    // Duplicate guessed Char
                    if (guessHistory.contains(inputChar)){
                        System.out.println("이미 한번 예측을 시도 했던 문자 입니다.");
                    }
                    // Exclude number.
                    else if (Character.isDigit(inputChar)) {
                        System.out.println("숫자를 입력했습니다.");
                    }
                    // Exclude upper case.
                    else if (Character.isUpperCase(inputChar)) {
                        System.out.println("대문자를 입력했습니다.");
                    }
                    // Correct input form.
                    else if (Character.isLowerCase(inputChar)) {
                        returnChar = inputChar;
                        guessHistory.add(returnChar);
                        break;
                    }
                    // Except.
                    else {
                        System.out.println("소문자로 1개의 영문자를 입력하세요.");
                    }
                } else {
                    System.out.println("1개의 문자만 입력해야 합니다.");
                }
            } catch (Exception e) {
                System.out.println("입력이 잘못되었습니다. 오류 메시지: " + e.getMessage());
            }
            System.out.print("다시 입력하세요: ");
        }

        return returnChar;
    }

    private void writeLogHistory(){
        logHistory.put(gameId, log.clone());
    }
    
    private void writeLog(int roundId, char userInput){
        log[roundId-1] = String.format("라운드 id : %d, 남은 목숨 : %d, %s, 사용자 입력 : %c", 
                                        roundId,
                                        lives,
                                        new String(guessedWord),
                                        userInput);
    }

    private void writeGameResult(){
        String resultString = "=== Game Result ===\n";
        resultString += String.format("게임 id : %d, 추측 : %s, 남은 목숨 : %d, 정답 : %s", 
                        gameId,
                        (gameSuccess) ? "성공" : "실패",
                        lives,
                        answer) + "\n\n";

        for (String logString : log){
            if (logString != null){
                resultString += logString + "\n";
            }
        }
        resultString += "===================\n";

        gameResult.put(gameId, resultString);
    }

    private void printGameResult(){
        boolean nextGameExist = (gameId == maxGameId) ? false : true;

        if (gameSuccess){
            System.out.println("축하합니다. 정답입니다.\n");
        }
        else{
            System.out.println("목숨이 모두 소진되었습니다. 실패했습니다. 정답은 " + answer + "입니다.\n");
        }

        if (nextGameExist){
            System.out.println("다음 게임을 시작합니다.\n");
        }
        else{
            System.out.println("모든 게임이 종료되었습니다.\n");
        }

        printTotalResult(gameId);
    }

    private void printTotalResult(int selectGameId){
        boolean isValidGameId = gameResult.containsKey(selectGameId);

        if (isValidGameId){
            System.out.println(gameResult.get(selectGameId));
        }else{
            System.out.println("존재하지 않는 게임 id 입니다.");
        }
    }

    private void printGameInfo() {
        System.out.println(gameId + "번째 게임이 시작됩니다. 정답 단어는 " + answer.length() + "글자 입니다.");
    }

    private void printGameRound() {
        System.out.println("라운드 : " + new String(guessedWord) + ", 목숨 " + lives);
    }

    private void printLog(int selectGameId, int selectRoundId){
        try {
            System.out.println(logHistory.get(selectGameId)[selectRoundId-1]);
        } catch (Exception e) {
            System.out.println("존재하지 않는 게임 id 또는 라운드 id 입니다.");
        }
    }
    
    public void showMenu(){
        String menu = "\n메뉴를 선택합니다. (1 : 게임하기, 2 : 게임 결과 보기, 3 : 라운드 결과 보기, 4 : 종료)";
        String errorMessage = "숫자 1, 2, 3, 4 중에 입력해주세요.";
        boolean stop = false;
        int menuSelection;
        int selectGameId;
        
        while (stop != true){
            System.out.println(menu);

            try{
                menuSelection = scanner.nextInt();
                switch (menuSelection) {
                    case 1:
                        start();
                        break;

                    case 2:
                        System.out.print("게임 id를 입력해주세요 : ");
                        selectGameId = scanner.nextInt();
                        printTotalResult(selectGameId);
                        break;
                    
                    case 3:
                        System.out.print("게임 id를 입력해주세요 : ");
                        selectGameId = scanner.nextInt();

                        System.out.print("라운드 id를 입력해주세요 : ");
                        int selectRoundId = scanner.nextInt();

                        printLog(selectGameId, selectRoundId);
                        break;

                    case 4:
                        System.out.println("종료합니다.");
                        stop = true;
                        break;

                    default:
                        System.out.println(errorMessage);
                }

            } catch (Exception e){
                System.out.println(errorMessage);
            } 
        }
    }

    public static void main(String[] args) {
        HangManApplication hangManGame = new HangManApplication();
        hangManGame.showMenu();
        hangManGame.scanner.close();
    }
}
