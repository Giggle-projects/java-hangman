package src;

import java.util.Scanner;

public class HangManApplication {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] inputs = input.split(",");
        int numberGames = Integer.parseInt(inputs[0]);
        int numberLives = Integer.parseInt(inputs[1]);

    }
}
