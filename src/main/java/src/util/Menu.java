package src.util;

import src.problem.ProblemList;
import src.exception.InputCategoryRangeException;

import java.util.LinkedHashMap;
import java.util.Map;

public class Menu {
    private static final Map<Integer, ProblemList> GAME_CATEGORY = new LinkedHashMap<>();


    static {
        GAME_CATEGORY.put(1, ProblemList.ANIMAL);
        GAME_CATEGORY.put(2, ProblemList.BODY);
    }

    public static String chooseCategory(){
        try {
            printCategory();
            System.out.println(Message.MSG_CHOOSE_CATEGORY);
            final int categoryNum = Utils.getInt();
            String categoryName = GAME_CATEGORY.get(categoryNum).getName();
            if (categoryNum < 1 || categoryNum > GAME_CATEGORY.size()) {
                throw new InputCategoryRangeException();
            }
            return categoryName;
        } catch (InputCategoryRangeException e) {
            System.out.println(e.getMessage());
            return chooseCategory();
        }
    }

    private static void printCategory(){
        for (int categoryNum : GAME_CATEGORY.keySet()) {
            final ProblemList ex = GAME_CATEGORY.get(categoryNum);
            System.out.println(categoryNum + ". " + ex.name());
        }
    }

}
