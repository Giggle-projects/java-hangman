package src.result;

public class RoundResult {

    private int roundId = 0;
    private int numOfLife;
    private String discoveredWord;
    private char strFromUser;

    public static int ROUND_ID = 0;

    public RoundResult() {
        ROUND_ID++;
    }

    public void startRound() {
        roundId++;
    }

    public RoundResult(int numOfLife, String discoveredWord, char strFromUser) {
        this();
        this.numOfLife = numOfLife;
        this.discoveredWord = discoveredWord;
        this.strFromUser = strFromUser;
    }

    public int getRoundId() {
        return roundId;
    }

    public void setRoundId(int roundId) {
        this.roundId = roundId;
    }

    public int getNumOfLife() {
        return numOfLife;
    }

    public void setNumOfLife(int numOfLife) {
        this.numOfLife = numOfLife;
    }

    public String getDiscoveredWord() {
        return discoveredWord;
    }

    public void setDiscoveredWord(String discoveredWord) {
        this.discoveredWord = discoveredWord;
    }

    public char getStrFromUser() {
        return strFromUser;
    }

    public void setStrFromUser(char strFromUser) {
        this.strFromUser = strFromUser;
    }

    @Override
    public String toString() {
        return "RoundResult{" +
                "numOfLife=" + numOfLife +
                ", discoveredWord='" + discoveredWord + '\'' +
                ", strFromUser=" + strFromUser +
                '}';
    }
}
