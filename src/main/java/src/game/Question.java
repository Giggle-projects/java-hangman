package src.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Question {
    private String targetQuestion;
    private String enteredAnswer;

    public Question(String targetQuestion) {
        this.targetQuestion = targetQuestion;
        this.enteredAnswer = "_".repeat(targetQuestion.length());
    }

    public String getTargetQuestion() {
        return targetQuestion;
    }

    public String getEnteredAnswer() {
        return enteredAnswer;
    }

    public void guessingWords(int life){
        int round = 1;
        String enteredAnswer = getEnteredAnswer();

        while (life > -1) {
            System.out.println(round + " 라운드 : " + enteredAnswer + ", 목숨 " + life);

            char targetChar = getChar();
            StringBuilder answer = new StringBuilder(enteredAnswer);

            if (targetQuestion.indexOf(targetChar) == -1)
                life--;
            else {
                for (int i = 0; i < targetQuestion.length(); i++) {
                    if (targetQuestion.charAt(i) == targetChar) {
                        answer.setCharAt(i, targetChar);
                    }
                }
            }
            enteredAnswer = answer.toString();

            round++;

            if (targetQuestion.contentEquals(enteredAnswer)) {
                System.out.println(round + " 라운드 : " + enteredAnswer + ", 목숨 " + life);
                System.out.println("축하합니다. 정답입니다.");
                life = -1;
            }

            if (life == 0){
                System.out.println("주어진 목숨을 전부 사용하셨습니다.");
                life = -1;
            }
        }
    }

    public static char getChar()  {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String targetInput = br.readLine().toLowerCase();

            if (targetInput.length() != 1) {
                throw new IllegalArgumentException("한 글자만 입력해주세요.");
            }
            char targetChar = targetInput.charAt(0);
            if (targetChar < 'a' || targetChar > 'z')
                throw new IllegalArgumentException("a-z, A-Z 사이의 글자만 입력해주세요.");
            return targetChar;
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            return getChar();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "Question{" +
                "targetQuestion='" + targetQuestion + '\'' +
                ", enteredAnswer='" + enteredAnswer + '\'' +
                '}';
    }
}
