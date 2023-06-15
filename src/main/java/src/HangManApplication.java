package src;

import src.userManager.User;
import src.userManager.UserList;

import java.util.*;

public class HangManApplication {
    private static final List<Game> games = new ArrayList<>();
    private static final UserList userList = new UserList();

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("===================");
                System.out.println("메뉴를 선택합니다. (1 : 게임하기, 2 : 게임 결과 보기, 3 : 라운드 결과 보기, 4 : 게임종료)");
                if (scanner.hasNextInt()) {
                    int menuNum = scanner.nextInt();
                    switch (menuNum) {
                        case 1 -> playGame(scanner);
                        case 2 -> showGameResult();
                        case 3 -> showRoundResult();
                        case 4 -> {
                            System.out.println("종료되었습니다.");
                            return;
                        }
                        default -> System.out.println("잘못된 번호입니다.");
                    }
                } else {
                    System.out.println("숫자를 입력해주세요.");
                    scanner.next();
                }
            }
        }
    }

    public static void playGame(Scanner scanner) {
        boolean isValidInput = false;
        int gameId = 1;
        scanner.nextLine(); // 개행문자 제거
        while (!isValidInput) {
            try {
                System.out.println("게임 횟수와 목숨을 입력하세요.");
                String str = scanner.nextLine();
                String[] input = str.split(",");
                if (input.length < 2) {
                    throw new IllegalArgumentException("숫자, 숫자 형태로 입력해주세요.");
                }
                int gameNum = Integer.parseInt(input[0].trim());
                int life = Integer.parseInt(input[1].trim());
                User user = new User("inyoung", life, gameNum);
                userList.addUser(user);
                isValidInput = true;

                for (int i = 0; i < user.getGameNum(); i++) {
                    String answer = GameAnswer.setAnswer();
                    char[] blind = GameAnswer.hideAnswer(answer);
                    if (i != 0) {
                        System.out.println("\n다음 게임을 시작합니다.");
                    }
                    System.out.println(gameId + "번째 게임이 시작됩니다. 정답 단어는 " + answer.length() + "글자 입니다.");
                    playRound(gameId, user.getLife(), answer, blind, scanner);
                    gameId++;
                }
            } catch (NumberFormatException e) {
                System.out.println("숫자, 숫자 형태로 입력해주세요.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void playRound(int gameId, int userLife, String answer, char[] blind, Scanner scanner) {
        int roundId = 1;
        Set<Character> prevAlp = new HashSet<>();
        List<Round> gameRound = new ArrayList<>();

        while (userLife > 0) {
            String blindScreen = String.valueOf(blind);
            System.out.println(roundId + " 라운드 : " + blindScreen + ", 목숨 " + userLife);

            char guess = getValidAlphabetInput(scanner);
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
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("게임 ID를 입력하세요.");
            int gameId = getValidInput(scanner, "게임 ID를 입력하세요: ");

            if (gameId <= 0 || gameId > games.size()) {
                System.out.println("해당 결과가 존재하지 않습니다.");
            } else {
                GameResult(gameId);
            }
        }
    }

    public static void showRoundResult() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("게임 ID를 입력하세요.");
            int gameId = getValidInput(scanner, "게임 ID를 입력하세요: ");

            if (gameId <= 0 || gameId > games.size()) {
                System.out.println("해당 결과가 존재하지 않습니다.");
                return;
            }

            int roundId = getValidInput(scanner, "라운드 ID를 입력하세요: ");

            Game game = games.get(gameId - 1);
            List<Round> roundList = game.getRounds();

            if (roundId <= 0 || roundId > roundList.size()) {
                System.out.println("해당 결과가 존재하지 않습니다.");
            } else {
                RoundResult(gameId, roundId);
            }
        }
    }

    private static int getValidInput(Scanner scanner, String msg) {
        while (true) {
            try {
                System.out.print(msg);
                int input = scanner.nextInt();
                scanner.nextLine();
                return input;
            } catch (InputMismatchException e) {
                System.out.println("숫자를 입력해주세요.");
                scanner.nextLine();
            }
        }
    }

    private static char getValidAlphabetInput(Scanner scanner) {
        while (true) {
            try {
                System.out.print("알파벳을 입력해주세요.");
                String input = scanner.nextLine().toLowerCase();
                if (input.length() == 1 && Character.isLetter(input.charAt(0))) {
                    return input.charAt(0);
                } else {
                    System.out.println("알파벳을 입력해주세요.");
                }
            } catch (InputMismatchException e) {
                System.out.println("알파벳을 입력해주세요.");
            }
        }
    }
}
