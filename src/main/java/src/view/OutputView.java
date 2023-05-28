package src.view;

public class OutputView {

    private static final String OU = "ou : ";

    // Suppresses default constructor, ensuring non-instantiability.
    private OutputView() {}

    public static void printMessage(String message) {
        System.out.println(OU + message);
    }
}
