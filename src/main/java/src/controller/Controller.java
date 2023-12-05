package src.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import src.view.InputView;
import src.view.OutputView;

public class Controller {

    public void run() {
        OutputView.printStartGame();
        String s = InputView.userInsert();
        String[] split = s.split(",");
        int[] countAndLife = Arrays.stream(split).mapToInt(Integer::parseInt).toArray();
        int count = countAndLife[0];
        int life = countAndLife[1];
        String s1 = randomWords();
        String temp ="";
        String replaceComma = "_";
        for (int i = 0; i < s1.length(); i++) {
            temp += replaceComma;
        }

        System.out.println(count+"번째 게임이 시작됩니다. 정답 단어는 "+s1.length()+"글자 입니다.");
        while (true){
            int round = 0;
            round++;
            System.out.println(round+" 라운드 :" + temp+","+life);
            String userAnswer = InputView.userInsert();

            String[] split1 = s1.split("");

            List<String> splitAnswer = Arrays.stream(split1).collect(Collectors.toList());

            StringBuilder result = new StringBuilder();
            if(splitAnswer.contains(userAnswer)){
                // 정답 apple , 사용자가 입력한 값이 a temp => a____
                for (int i = 0; i < splitAnswer.size(); i++) {
                    if(Objects.equals(splitAnswer.get(i), userAnswer)){
                        result.append(userAnswer);
                    }else {
                        result.append("_");
                    }
                }
            }

            if(life < 0 ){
                break;
            }
        }




    }

    private static String randomWords() {
        List<String> wordsList = List.of("banana", "apple", "carrot", "orange");
        List<String> words = new ArrayList<>(wordsList);
        Collections.shuffle(words);
        String s = words.get(0);
        System.out.println(s);
        return s;
    }

}
