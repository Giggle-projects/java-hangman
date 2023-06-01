package src.result;

public class RoundResult {

    private static int ROUND_ID = 0;
    private int numOfLife;
    private String discoveredWord;
    private char strFromUser;

    public RoundResult() {
    }

    public void startRound() {
        ROUND_ID++;
    }

    public RoundResult(int numOfLife, String discoveredWord, char strFromUser) {
        ROUND_ID++;
        this.numOfLife = numOfLife;
        this.discoveredWord = discoveredWord;
        this.strFromUser = strFromUser;
    }

    public static int getRoundId() {
        return ROUND_ID;
    }

    public static void setRoundId(int roundId) {
        ROUND_ID = roundId;
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
