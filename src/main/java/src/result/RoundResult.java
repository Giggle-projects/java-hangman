package src.result;

public class RoundResult {
    private int id;
    private int life;
    private String answerChange;
    private char inputData;

    public RoundResult(int id, int life, String answerChange, char inputData) {
        this.id = id;
        this.life = life;
        this.answerChange = answerChange;
        this.inputData = inputData;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public String getAnswerChange() {
        return answerChange;
    }

    public void setAnswerChange(String answerChange) {
        this.answerChange = answerChange;
    }

    public char getInputData() {
        return inputData;
    }

    public void setInputData(char inputData) {
        this.inputData = inputData;
    }
}
