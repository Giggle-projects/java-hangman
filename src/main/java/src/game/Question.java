package src.game;

public class Question {
    private final String targetQuestion;
    private final String enteredAnswer;

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

    @Override
    public String toString() {
        return "Question{" +
                "targetQuestion='" + targetQuestion + '\'' +
                ", enteredAnswer='" + enteredAnswer + '\'' +
                '}';
    }

    public boolean checkCharInWord(char targetChar){
        return getTargetQuestion().indexOf(targetChar) == -1;
    }

    public boolean validAnswer(String enteredAnswer){
        return getTargetQuestion().contentEquals(enteredAnswer);
    }
}
