package src.util;

import src.exception.InputCategoryRangeException;
import src.exception.InputIntRangeException;
import src.problem.ProblemList;

import java.util.LinkedHashMap;
import java.util.Map;

public class InputMenu {

    private static final Map<Integer, Menu> MENU_GROUP = new LinkedHashMap<>();
    private static final Map<Integer, ProblemList> GAME_CATEGORY = new LinkedHashMap<>();

    static {
        GAME_CATEGORY.put(1, ProblemList.ANIMAL);
        GAME_CATEGORY.put(2, ProblemList.BODY);

        MENU_GROUP.put(1, Menu.PLAY_GAME);
        MENU_GROUP.put(2, Menu.SHOW_GAME_RESULT);
        MENU_GROUP.put(3, Menu.SHOW_ROUND_RESULT);
        MENU_GROUP.put(4, Menu.END);
    }

    public static Menu chooseMenu(){
        try {
            printMenu();
            final int num = Utils.getInt();
            if(num < 1 || num > MENU_GROUP.size())
                throw new InputIntRangeException();
            return MENU_GROUP.get(num);
        } catch (InputIntRangeException e){
            System.out.println(e.getMessage());
            return chooseMenu();
        }

    }

    private static void printMenu(){
        System.out.print("메뉴를 선택합니다. ");
        for (int menuNumber : MENU_GROUP.keySet()){
            final Menu menu = MENU_GROUP.get(menuNumber);
            System.out.print(menuNumber + " : " + menu.getName());
            if (!MENU_GROUP.get(menuNumber).isEnd())
                System.out.print(", ");
        }
        System.out.println();
    }

    public static String chooseProblemType(){
        try {
            printCategory();
            System.out.println(Message.MSG_CHOOSE_CATEGORY);
            final int categoryNum = Utils.getInt();
            if (categoryNum < 1 || categoryNum > GAME_CATEGORY.size()) {
                throw new InputCategoryRangeException();
            }
            String problemType = GAME_CATEGORY.get(categoryNum).getProblemType();
            return problemType;
        } catch (InputCategoryRangeException e) {
            System.out.println(e.getMessage());
            return chooseProblemType();
        }
    }

    private static void printCategory(){
        for (int categoryNum : GAME_CATEGORY.keySet()) {
            final ProblemList ex = GAME_CATEGORY.get(categoryNum);
            System.out.println(categoryNum + ". " + ex.name());
        }
    }

}
