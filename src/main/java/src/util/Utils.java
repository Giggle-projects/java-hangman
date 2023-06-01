package src.util;

import src.exception.InputCharFormatException;
import src.exception.InputCharRangeException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Utils {
    private static BufferedReader br;

    public static List<Integer> inputGameRoundAndLife() {
        if (br == null) {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        List<Integer> inputArr = new ArrayList<>();
        String inputLine;

        while (true) {
            try {
                inputArr.clear();
                inputLine = br.readLine();
                StringTokenizer st = new StringTokenizer(inputLine, ",");

                for (int i = 0; i < 2; i++) {
                    inputArr.add(Integer.parseInt(st.nextToken()));
                }

                if (st.hasMoreTokens()) {
                    System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);
                    continue;
                }

                System.out.println(inputArr);
                return inputArr;

            } catch (NumberFormatException e){
                System.out.println(Message.ERR_MSG_INVALID_INPUT_NUMBER_FORMAT);
            } catch (NoSuchElementException e){
                System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static int getInt() {
        try {
            return Integer.parseInt(new Scanner(System.in).nextLine());
        } catch (NumberFormatException e) {
            System.out.println(Message.ERR_MSG_INVALID_INPUT_NUMBER_FORMAT);
            return getInt();
        }
    }

    public static char getChar() {
        if (br == null) {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        while (true) {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String targetInput = br.readLine().toLowerCase();

                if (targetInput.length() != 1) {
                    throw new InputCharRangeException();
                }
                char targetChar = targetInput.charAt(0);
                if (targetChar < 'a' || targetChar > 'z')
                    throw new InputCharFormatException();
                return targetChar;
            } catch (InputCharFormatException | InputCharRangeException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }
}
