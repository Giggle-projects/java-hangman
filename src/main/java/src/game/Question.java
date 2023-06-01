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
}
