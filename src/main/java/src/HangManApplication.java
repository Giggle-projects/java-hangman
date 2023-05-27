package src;

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
        User user = new User();
        User user2 = new User();
        System.out.println(user.getId());
        System.out.println(user.getRegDate());
        System.out.println(user2.getId());
        System.out.println(user2.getRegDate());

        int[] arr = game.initGame();
        game.startGame(arr[0], arr[1]);
    }
}
