package src.util;

import src.exception.InputCharFormatException;
import src.exception.InputCharRangeException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Utils {

    public static int getInt() {
        try {
            return Integer.parseInt(new Scanner(System.in).nextLine());
        } catch (NumberFormatException e) {
            System.out.println(Message.ERR_MSG_INVALID_INPUT_NUMBER_FORMAT);
            return getInt();
        }
    }

    public static char getChar()  {
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
