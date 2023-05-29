package src;

import src.exception.IllegalInputLengthException;
import src.util.Message;

import java.util.Scanner;

public class Input {

    /**
     * 게임 초기 설정값을 받을 때 사용할 메서드
     * @return
     */
    public static int[] getInitiationParam() {
        try {
            int[] res = new int[2];
            // 이거 next()로 받으면 예외 터짐
            String input = new Scanner(System.in).nextLine();
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
            String str = new Scanner(System.in).next();
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
