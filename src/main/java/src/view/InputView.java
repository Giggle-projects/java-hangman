package src.view;

import java.util.Scanner;

public class InputView {
    private InputView() {
    }

    static Scanner sc = new Scanner(System.in);

    public static String userInsert(){
        return sc.nextLine();
    }

    public static int userNumber (){
        return sc.nextInt();
    }


}
