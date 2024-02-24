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
    private Scanner scanner = new Scanner(System.in);

    public void start() {
        // Set the number of games and the number of lives.
        initializeSettings();

        for (int i = 1; i <= maxGameId; i++) {
            gameId = i;
            initializeGame();
            playGame();

            HangManDataHandler.writeGameResult(gameResult, gameId, gameSuccess, lives, answer, log);
            HangManDataHandler.writeLogHistory(logHistory, gameId, log);
            HangManPrinter.printGameResult(gameSuccess, maxGameId, gameId, lives, answer);
            HangManPrinter.printTotalResult(gameResult, gameId);
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
        this.guessHistory = new HashSet<>();
        this.answer = WORDS[(int)(Math.random()*WORDS.length)];
        this.lives = maxLives;
        this.log = new String[answer.length() + maxLives];
        this.guessedWord = new char[answer.length()];
        for (int i = 0; i < answer.length(); i++) {
            guessedWord[i] = '_';
        }

        HangManPrinter.printGameInfo(gameId, answer);
    }

    private void playGame() {
        int roundId = 1;

        while (lives > 0) {
            HangManPrinter.printGameRound(guessedWord, lives);

            char guess = getUserGuess();

            boolean guessSuccess = checkGuessSuccess(guess);

            if (gameSuccess){
                break;
            }

            if (!guessSuccess){
                lives--;
            }
            
            //String[] log, int roundId, char userInput, int lives, char[] guessedWord
            HangManDataHandler.writeLog(log, roundId, guess, lives, guessedWord);
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

    private char getUserGuess() {
        char returnChar = 0;
    
        System.out.print("문자를 입력하세요: ");
    
        while (true) {
            try {
                String userInput = scanner.next();
                returnChar = processUserInput(userInput);
                if (returnChar != 0) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("입력이 잘못되었습니다. 오류 메시지: " + e.getMessage());
            }
            System.out.print("다시 입력하세요: ");
        }
    
        return returnChar;
    }
    
    private char processUserInput(String userInput) {
        // Check length.
        if (userInput.length() != 1) {
            System.out.println("1개의 문자만 입력해야 합니다.");
            return 0;
        }
    
        char inputChar = userInput.charAt(0);
    
        // Duplicate guessed Char
        if (guessHistory.contains(inputChar)) {
            System.out.println("이미 한번 예측을 시도 했던 문자 입니다.");
            return 0;
        }
    
        // Exclude number.
        if (Character.isDigit(inputChar)) {
            System.out.println("숫자를 입력했습니다.");
            return 0;
        }
    
        // Exclude upper case.
        if (Character.isUpperCase(inputChar)) {
            System.out.println("대문자를 입력했습니다.");
            return 0;
        }
    
        // Correct input form.
        if (Character.isLowerCase(inputChar)) {
            guessHistory.add(inputChar);
            return inputChar;
        }
    
        // Except.
        System.out.println("소문자로 1개의 영문자를 입력하세요.");
        return 0;
    }
    
    public void showMenu(){
        String menu = "\n메뉴를 선택합니다. (1 : 게임하기, 2 : 게임 결과 보기, 3 : 라운드 결과 보기, 4 : 종료)";
        String errorMessage = "숫자 1, 2, 3, 4 중에 입력해주세요.";
        int menuSelection;
        int selectGameId;
        
        while (true){
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
                        HangManPrinter.printTotalResult(gameResult, selectGameId);
                        break;
                    
                    case 3:
                        System.out.print("게임 id를 입력해주세요 : ");
                        selectGameId = scanner.nextInt();

                        System.out.print("라운드 id를 입력해주세요 : ");
                        int selectRoundId = scanner.nextInt();

                        HangManPrinter.printLog(logHistory, selectGameId, selectRoundId);
                        break;

                    case 4:
                        System.out.println("종료합니다.");
                        return;

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