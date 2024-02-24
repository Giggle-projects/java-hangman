import java.util.*;

class HangManDataHandler {
    public static void writeLogHistory(HashMap<Integer, String[]> logHistory, int gameId, String[] log) {
        logHistory.put(gameId, log.clone());
    }

    public static void writeLog(String[] log, int roundId, char userInput, int lives, char[] guessedWord) {
        log[roundId - 1] = String.format("라운드 id : %d, 남은 목숨 : %d, %s, 사용자 입력 : %c",
                roundId,
                lives,
                new String(guessedWord),
                userInput);
    }

    public static void writeGameResult(HashMap<Integer, String> gameResult, int gameId, boolean gameSuccess, int lives, String answer, String[] log) {
        String resultString = "=== Game Result ===\n";
        resultString += String.format("게임 id : %d, 추측 : %s, 남은 목숨 : %d, 정답 : %s",
                gameId,
                (gameSuccess) ? "성공" : "실패",
                lives,
                answer) + "\n\n";

        for (String logString : log) {
            if (logString != null) {
                resultString += logString + "\n";
            }
        }
        resultString += "===================\n";

        gameResult.put(gameId, resultString);
    }
}