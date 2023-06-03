package src;

import src.exception.IllegalInputLengthException;
import src.exception.InputMenuException;
import src.util.Message;

import java.util.Scanner;

import static src.Game.gameResults;

public class Input {

    public static Game game = Game.getInstance();

    static Scanner scanner = new Scanner(System.in);

    public static void chooseMenu() {
        while (true) {
            String[] menus = {"게임하기", "게임 결과 보기", "라운드 결과 보기"};
            try {
                System.out.printf("메뉴를 선택합니다. (");
                for (int i=0; i<menus.length; i++) {
                    if (i == menus.length - 1) {
                        System.out.printf("%d : %s", i+1, menus[i]);
                    } else {
                        System.out.printf("%d : %s, ", i+1, menus[i]);
                    }
                }
                System.out.printf(") : \n");

                String str = scanner.next();
                int choice = Integer.parseInt(str);

                if (choice == 1) {
                    int[] arr = game.initGame();
                    int numOfGame = arr[0];
                    int numOfLife = arr[1];
                    game.startGame(numOfGame, numOfLife);
                } else if (choice == 2) {
                    if (gameResults.isEmpty()) {
                        System.out.println("게임 정보가 없습니다. 게임을 먼저 진행해 주십시오.");
                        chooseMenu();
                    }
                    System.out.printf("게임 ID를 입력해 주세요 : ");
                    String input = scanner.next();
                    Integer numOfGameId = Integer.parseInt(input);
                    Game.findGameById(numOfGameId);
                    chooseMenu();
                } else if (choice == 3) {
                    if (gameResults.isEmpty()) {
                        System.out.println("게임 정보가 없습니다. 게임을 먼저 진행해 주십시오.");
                        chooseMenu();
                    }
                    System.out.printf("라운드 ID를 입력해 주세요 : ");
                    String input = scanner.next();
                    Integer numOfRoundId = Integer.parseInt(input);
                    Game.findRoundById(numOfRoundId);
                    chooseMenu();
                } else {
                    throw new InputMenuException(Message.ERR_OUT_OF_MENU_BOUNDARY);
                }

            } catch (NumberFormatException e) {
                System.out.println(Message.ERR_NUMBER_FORMAT_EXCEPTION);
                chooseMenu();
            } catch (InputMenuException e) {
                System.out.println(Message.ERR_OUT_OF_MENU_BOUNDARY);
                chooseMenu();
            } catch (Exception e) {
                System.out.println(Message.ERR_UNKNOWN_ERROR);
                System.exit(0);
            }
        }
    }

    public static int[] getInitiationParam() {
        try {
            scanner.nextLine(); // 버퍼를 비워주기 위한 라인
            int[] res = new int[2];
            String input = scanner.nextLine();
            String[] splittedArr = input.split(",");

            if (splittedArr.length != 2) {
                throw new IllegalArgumentException(Message.ERR_NOT_USING_COMMA);
            }

            res[0] = Integer.parseInt(splittedArr[0].trim());
            res[1] = Integer.parseInt(splittedArr[1].trim());
            return res;
        } catch (IllegalInputLengthException e) {
            System.out.println(e.getMessage());
            return getInitiationParam();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getInitiationParam();
        }
    }

    public static char getGameParam() {
        try {
            String str = scanner.next();
            if (str.length() > 1) {
                throw new IllegalInputLengthException();
            }
            str = str.toUpperCase();
            return str.charAt(0);
        } catch (IllegalInputLengthException e) {
            System.out.println(Message.ERR_INPUT_RANGE);
            return getGameParam();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getGameParam();
        }
    }
}
