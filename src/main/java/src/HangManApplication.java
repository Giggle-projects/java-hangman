package src;

public class HangManApplication {

    private static final HangManApplication hangManApplication = new HangManApplication();

    public static HangManApplication getInstance() {
        return hangManApplication;
    }

    public static void main(String[] args) {
        run();
    }

    private static void run() {
        Input.chooseMenu();
    }
}
