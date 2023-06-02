package src;

import src.exception.IllegalInputLengthException;
import src.util.Message;

import java.util.Scanner;

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
                    //game.printGameScore();
                } else if (choice == 3) {
                    game.getRoundScore();
                }

            } catch (NumberFormatException e) {
                System.out.println(Message.ERR_NUMBER_FORMAT_EXCEPTION);
                chooseMenu();
            } catch (Exception e) {
                System.out.println(Message.ERR_UNKNOWN_ERROR);
                System.exit(0);
            }
        }
    }

    /**
     * 게임 초기 설정값을 받을 때 사용할 메서드
     * @return
     */
    public static int[] getInitiationParam() {
        try {
            int[] res = new int[2];
            // 이거 next()로 받으면 예외 터짐
            String input = scanner.nextLine();
            String[] splittedArr = input.split(",");

            if (splittedArr.length != 2) {
                throw new IllegalArgumentException("두 개의 숫자를 콤마로 구분하여 입력하시오.");
            }

            res[0] = Integer.parseInt(splittedArr[0].trim());
            res[1] = Integer.parseInt(splittedArr[1].trim());
            return res;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getInitiationParam();
        }
    }

    /**
     * 게임 중 계속해서 값을 받을 때 사용할 메서드
     * @return
     */
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
