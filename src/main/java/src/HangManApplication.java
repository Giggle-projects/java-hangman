package src;

public class HangManApplication {
    private static HangManApplication hangManApplication;

    public static HangManApplication getInstance() {
        if (hangManApplication == null) {
            hangManApplication = new HangManApplication();
        }
        return hangManApplication;
    }
    public static void main(String[] args) {

    }
}
