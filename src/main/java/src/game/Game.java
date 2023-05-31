package src.game;

import src.example.Example;
import src.example.ExampleList;
import src.exception.InputCategoryRangeException;
import src.util.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Game {
    private static final int MAX_GAME_ROUND = 20;
    private static final Map<Integer, ExampleList> GAME_CATEGORY = new LinkedHashMap<>();

    static {
        GAME_CATEGORY.put(1, ExampleList.ANIMAL);
        GAME_CATEGORY.put(2, ExampleList.BODY);
    }

    private final int gameRound;
    private final int life;

    private Game(int gameRound, int life) {
        this.gameRound = gameRound;
        this.life = life;
    }

    public int getGameRound() {
        return gameRound;
    }

    public int getLife() {
        return life;
    }

    @Override
    public String toString() {
        return "Game{" +
                "gameRound=" + gameRound +
                ", life=" + life +
                '}';
    }

    public static Game createGame() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true){
            try {
                System.out.println(Message.MSG_GAME_START);
                String input = br.readLine();
                StringTokenizer st = new StringTokenizer(input, ",");

                int gameRound = Integer.parseInt(st.nextToken());
                int life = Integer.parseInt(st.nextToken());

                System.out.println(gameRound+ "," + life);

                if (st.hasMoreTokens()) {
                    System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);
                    continue;
                }

                if (gameRound > MAX_GAME_ROUND) {
                    System.out.println(Message.ERR_MSG_INVALID_INPUT_ROUND_RANGE);
                    continue;
                }

                return new Game(gameRound, life);
            } catch (NumberFormatException e){
                System.out.println(Message.ERR_MSG_INVALID_INPUT_NUMBER_FORMAT);
            } catch (NoSuchElementException e){
                System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static List<String> chooseCategory(int gameRound){

        while (true){
            try {
                printCategory();
                System.out.println(Message.MSG_CHOOSE_CATEGORY);
                final int categoryNum = getInt();
                String categoryName = GAME_CATEGORY.get(categoryNum).getName();
                if (categoryNum < 1 || categoryNum > GAME_CATEGORY.size()) {
                    throw new InputCategoryRangeException();
                }
                return createProblems(gameRound, categoryName);
            } catch (InputCategoryRangeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void printCategory(){
        for (int categoryNum : GAME_CATEGORY.keySet()) {
            final ExampleList ex = GAME_CATEGORY.get(categoryNum);
            System.out.println(categoryNum + ". " + ex.name());
        }
    }

    private static List<String> createProblems(int gameRound, String categoryName){
        List<String> problems;
        try {

            Class<?> enumClass = Class.forName("src.example."+categoryName);
            Example ex = (Example) enumClass.getEnumConstants()[0];
            problems = ex.getNameList();

            Collections.shuffle(problems);

            for (int i = 0; i < gameRound; i++) {
                String exampleName = problems.get(i);
                problems.add(exampleName);
            }
            return problems;

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static int getInt() {
        try {
            return Integer.parseInt(new Scanner(System.in).nextLine());
        } catch (NumberFormatException e) {
            System.out.println(Message.ERR_MSG_INVALID_INPUT_NUMBER_FORMAT);
            return getInt();
        }
    }
}
