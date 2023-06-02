package src;

public class HangManApplication {
    private static HangManApplication hangManApplication;
    private static Game game = Game.getInstance();

    public static HangManApplication getInstance() {
        if (hangManApplication == null) {
            hangManApplication = new HangManApplication();
        }
        return hangManApplication;
    }

    public static void main(String[] args) {
        run();
    }

    private static void run() {
        Input.chooseMenu();
    }
}
