package src;

import java.util.*;

public class HangManApplication {
    public static Scanner scanner = new Scanner(System.in);
    static User user = new User();
    private static List<Round> rounds = new ArrayList<>();
    private static List<Game> games = new ArrayList<>();
    private static boolean isSuccess;

    public static void main(String[] args) {
        while (true) {
            System.out.println("메뉴를 선택합니다. (1 : 게임하기, 2 : 게임 결과 보기, 3 : 라운드 결과 보기, 4 : 게임종료)");
            int menuNum = scanner.nextInt();
            switch (menuNum) {
                case 1 -> playGame();
                case 2 -> {
                    showGameResult(scanner);
                }
                case 3 -> {
                    showRoundResult(scanner);
                }
                case 4 -> {
                    return;
                }
                default -> System.out.println("잘못된 번호입니다.");
            }
        }
    }

    public static void playGame() {
        boolean isValidInput = false;
        int gameId = 1;
        while (!isValidInput) {
            try {
                System.out.println("게임 횟수와 목숨을 입력하세요.");
                String[] input = scanner.nextLine().split(",");
                if (input.length < 2) {
                    throw new IllegalArgumentException("숫자, 숫자 형태로 입력해주세요.");
                }
                int gameNum = Integer.parseInt(input[0].trim());
                int life = Integer.parseInt(input[1].trim());
                user.setGameNum(gameNum);
                user.setLife(life);
                isValidInput = true;
            } catch (NumberFormatException e) {
                System.out.println("숫자, 숫자 형태로 입력해주세요.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        int userLife = user.getLife();
        for (int i = 0; i < user.getGameNum(); i++) {
            String answer = GameAnswer.setAnswer();
            char[] blind = GameAnswer.setBlind(answer);
            if (i != 0) {
                System.out.println("\n다음 게임을 시작합니다.");
            }
            System.out.println(gameId + "번째 게임이 시작됩니다. 정답 단어는 " + answer.length() + "글자 입니다.");
            round(userLife, answer, blind, scanner);
            Game game = new Game(gameId, isSuccess, userLife, answer);
            games.add(game);
            GameResult(gameId);
            gameId++;
        }
    }

    private static void round(int userLife, String answer, char[] blind, Scanner scanner) {
        String blindScreen;

        int roundId = 1;
        Set<Character> prevAlp = new HashSet<>();

        while (userLife > 0) {
            blindScreen = String.valueOf(blind);
            System.out.println(roundId + " 라운드 : " + blindScreen + ", 목숨 " + userLife);
            boolean isValid = true;
            char ch = '\0';
            while (isValid) {
                ch = scanner.next().charAt(0);
                if (Character.isLowerCase(ch)) {
                    isValid = false;
                } else if (Character.isUpperCase(ch)) {
                    ch = Character.toLowerCase(ch);
                    isValid = false;
                } else {
                    System.out.println("알파벳을 입력해주세요.");
                }

                if (prevAlp.contains(ch)) {
                    System.out.println("이미 입력하였던 알파벳입니다.");
                    isValid = true;
                }
            }
            prevAlp.add(ch);

            boolean isMatch = false;
            for (int j = 0; j < answer.length(); j++) {
                if (answer.charAt(j) == ch) {
                    blind[j] = ch;
                    isMatch = true;
                }
            }
            if (!isMatch) {
                userLife--;
            }

            isSuccess = true;
            for (char c : blind) {
                if (c == '_') {
                    isSuccess = false;
                    break;
                }
            }

            Round result = new Round(roundId, userLife, blindScreen, ch);
            rounds.add(result);
            roundId++;

            if (isSuccess) {
                System.out.println("축하합니다. 정답입니다.");
                break;
            }
            if (userLife == 0) {
                System.out.println("정답 맞추기에 실패하셨습니다.");
            }
        }
    }

    private static void GameResult(int gameId) {
        int idx = gameId - 1;
        games.get(idx).PrintGameResult();

        for (Round round : rounds) {
            round.PrintRoundResult();
        }
    }

    public static void RoundResult(int roundId) {
        System.out.println("=== Round Result ===");
        rounds.get(roundId).PrintRoundResult();
    }

    public static void showGameResult(Scanner scanner) {
        System.out.println("게임 ID를 입력하세요.");
        try {
            int gameId = scanner.nextInt() - 1;
            if (gameId < 0) {
                throw new IllegalArgumentException("1 이상의 숫자만 입력해주세요.");
            } else {
                GameResult(gameId);
            }
        } catch (InputMismatchException e) {
            System.out.println("숫자를 입력해주세요.");
            scanner.nextLine();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            scanner.nextLine();
        }
    }

    public static void showRoundResult(Scanner scanner) {
        System.out.println("라운드 ID를 입력하세요.");
        try {
            int roundId = scanner.nextInt() - 1;
            if (roundId < 0) {
                throw new IllegalArgumentException("1 이상의 숫자만 입력해주세요.");
            } else if (roundId >= rounds.size() || rounds.isEmpty()) {
                System.out.println("해당 결과가 존재하지 않습니다.");
            } else {
                RoundResult(roundId);
            }
        } catch (InputMismatchException e) {
            System.out.println("숫자를 입력해주세요.");
            scanner.nextLine();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            scanner.nextLine();
        }
    }
}
