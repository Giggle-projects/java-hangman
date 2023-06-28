package src.util;

import src.exception.InputCategoryRangeException;
import src.exception.InputIntRangeException;
import src.problem.ProblemList;

import java.util.LinkedHashMap;
import java.util.Map;

public class InputMenu {

    private static final Map<Integer, Menu> MENU_GROUP = new LinkedHashMap<>();
    private static final Map<Integer, ProblemList> PROBLEM_TYPE = new LinkedHashMap<>();

    static {
        PROBLEM_TYPE.put(1, ProblemList.ANIMAL);
        PROBLEM_TYPE.put(2, ProblemList.BODY);

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
            printProblemType();
            System.out.println(Message.MSG_CHOOSE_CATEGORY);
            final int inputNum = Utils.getInt();
            if (inputNum < 1 || inputNum > PROBLEM_TYPE.size()) {
                throw new InputCategoryRangeException();
            }
            return PROBLEM_TYPE.get(inputNum).getProblemType();
        } catch (InputCategoryRangeException e) {
            System.out.println(e.getMessage());
            return chooseProblemType();
        }
    }

    private static void printProblemType(){
        for (int problemType : PROBLEM_TYPE.keySet()) {
            final ProblemList ex = PROBLEM_TYPE.get(problemType);
            System.out.println(problemType + ". " + ex.name());
        }
    }

}
