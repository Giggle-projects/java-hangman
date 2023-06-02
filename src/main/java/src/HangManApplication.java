package src;

import src.result.RoundResult;

public class HangManApplication {
    private static HangManApplication hangManApplication;
    private static Game game = Game.getInstance();

    private static int GAME_COUNT = 0;

    public static HangManApplication getInstance() {
        if (hangManApplication == null) {
            hangManApplication = new HangManApplication();
        }
        return hangManApplication;
    }

    public static void main(String[] args) {

        //RoundResult roundResult = new RoundResult(1, "aaaa", "aa");
//        User user = new User();
//        User user2 = new User();
//        System.out.println(user.getId());
//        System.out.println(user.getRegDate());
//        System.out.println(user2.getId());
//        System.out.println(user2.getRegDate());

        run();
    }

    private static void run() {
        Input.chooseMenu();
    }
}
