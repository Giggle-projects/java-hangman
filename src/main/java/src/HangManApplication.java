package src;

import src.userManager.User;
import src.userManager.UserList;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HangManApplication {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static final List<Game> games = new ArrayList<>();
    private static final UserList userList = new UserList();

    public static void main(String[] args) {
        while (true) {
            System.out.println("===================");
            System.out.println("메뉴를 선택합니다. (1 : 게임하기, 2 : 게임 결과 보기, 3 : 라운드 결과 보기, 4 : 게임종료)");

            try {
                int menuNum = getValidInput();
                switch (menuNum) {
                    case 1:
                        startGame();
                        break;
                    case 2:
                        showGameResult();
                        break;
                    case 3:
                        showRoundResult();
                        break;
                    case 4:
                        System.out.println("종료되었습니다.");
                        return;
                    default:
                        System.out.println("잘못된 번호입니다.");
                }
            } catch (IOException e) {
                System.out.println("입력 오류가 발생했습니다.");
            }
        }
    }

    public static void startGame() {
        try {
            System.out.println("게임 횟수와 목숨을 입력하세요.");
            String str = reader.readLine();
            String[] input = str.split(",");
            if (input.length < 2) {
                throw new IllegalArgumentException("숫자, 숫자 형태로 입력해주세요.");
            }
            int gameNum = Integer.parseInt(input[0].trim());
            int life = Integer.parseInt(input[1].trim());
            System.out.println("사용자 이름을 입력하세요.");
            String userName = reader.readLine();
            User user = new User(userName, life, gameNum);
            userList.addUser(user);
            int startGameID = 1;
            runGames(user, startGameID);
        } catch (NumberFormatException e) {
            System.out.println("숫자, 숫자 형태로 입력해주세요.");
            startGame();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            startGame();
        } catch (IOException e) {
            System.out.println("입력 오류가 발생했습니다.");
        }
    }

    private static void runGames(User user, int gameId) {
        if (gameId <= user.getGameNum()) {
            String answer = GameAnswer.setAnswer();
            char[] blind = GameAnswer.hideAnswer(answer);
            if (gameId != 1) {
                System.out.println("\n다음 게임을 시작합니다.");
            }
            System.out.println(gameId + "번째 게임이 시작됩니다. 정답 단어는 " + answer.length() + "글자 입니다.");
            playRound(gameId, user.getLife(), answer, blind);
            runGames(user, gameId + 1);
        }
    }

    private static void playRound(int gameId, int userLife, String answer, char[] blind) {
        int roundId = 1;
        Set<Character> prevAlp = new HashSet<>();
        List<Round> gameRound = new ArrayList<>();

        while (userLife > 0) {
            String blindScreen = String.valueOf(blind);
            System.out.println(roundId + " 라운드 : " + blindScreen + ", 목숨 " + userLife);

            try {
                char guess = getValidAlphabetInput();
                guess = Character.toLowerCase(guess);

                if (prevAlp.contains(guess)) {
                    System.out.println("이미 입력하였던 알파벳입니다.");
                    continue;
                }
                prevAlp.add(guess);

                boolean isMatch = false;
                for (int j = 0; j < answer.length(); j++) {
                    if (answer.charAt(j) == guess) {
                        blind[j] = guess;
                        isMatch = true;
                    }
                }

                if (!isMatch) {
                    userLife--;
                }
                Round result = new Round(roundId, userLife, blindScreen, guess);
                gameRound.add(result);
                roundId++;

                if (isSuccess(blind)) {
                    System.out.println("축하합니다. 정답입니다.");
                    break;
                }

                if (userLife == 0) {
                    System.out.println("정답 맞추기에 실패하셨습니다.");
                }
            } catch (IOException e) {
                System.out.println("입력 오류가 발생했습니다.");
            }
        }

        Game game = new Game(gameId, isSuccess(blind), userLife, answer, gameRound);
        games.add(game);
        GameResult(gameId);
    }

    private static boolean isSuccess(char[] blind) {
        for (char c : blind) {
            if (c == '_') {
                return false;
            }
        }
        return true;
    }

    private static void GameResult(int gameId) {
        int idx = gameId - 1;
        Game game = games.get(idx);
        game.printGameResult();
        System.out.println();
        List<Round> roundList = game.getRounds();
        for (Round round : roundList) {
            round.printRoundResult();
        }
        System.out.println("===================");
    }

    public static void RoundResult(int gameId, int roundId) {
        int gameIdx = gameId - 1;
        int roundIdx = roundId - 1;
        System.out.println("=== Round Result ===");
        Game game = games.get(gameIdx);
        List<Round> roundList = game.getRounds();
        if (roundIdx >= 0 && roundIdx < roundList.size()) {
            roundList.get(roundIdx).printRoundResult();
        } else {
            System.out.println("해당 라운드 결과가 존재하지 않습니다.");
        }
        System.out.println("===================");
    }

    public static void showGameResult() {
        if (games.isEmpty()){
            System.out.println("진행된 게임이 없습니다.");
            return;
        }
        try {
            System.out.println("게임 ID를 입력하세요.");
            int gameId = getValidInput();

            if (gameId <= 0 || gameId > games.size()) {
                System.out.println("해당 결과가 존재하지 않습니다.");
            } else {
                GameResult(gameId);
            }
        } catch (IOException e) {
            System.out.println("입력 오류가 발생했습니다.");
        }
    }

    public static void showRoundResult() {
        if (games.isEmpty()){
            System.out.println("진행된 게임이 없습니다.");
            return;
        }
        try {
            System.out.println("게임 ID를 입력하세요.");
            int gameId = getValidInput();

            if (gameId <= 0 || gameId > games.size()) {
                System.out.println("해당 결과가 존재하지 않습니다.");
                return;
            }

            System.out.println("라운드 ID를 입력하세요.");
            int roundId = getValidInput();

            Game game = games.get(gameId - 1);
            List<Round> roundList = game.getRounds();

            if (roundId <= 0 || roundId > roundList.size()) {
                System.out.println("해당 결과가 존재하지 않습니다.");
            } else {
                RoundResult(gameId, roundId);
            }
        } catch (IOException e) {
            System.out.println("입력 오류가 발생했습니다.");
        }
    }

    private static int getValidInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return Integer.parseInt(reader.readLine());
        } catch (NumberFormatException e) {
            throw new IOException("숫자를 입력해주세요.");
        }
    }

    private static char getValidAlphabetInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String input = reader.readLine();
            if (input.length() == 1 && Character.isLetter(input.charAt(0))) {
                return Character.toLowerCase(input.charAt(0));
            } else {
                throw new IOException("알파벳을 입력해주세요.");
            }
        } catch (NumberFormatException e) {
            throw new IOException("알파벳을 입력해주세요.");
        }
    }
}