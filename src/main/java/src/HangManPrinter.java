import java.util.HashMap;

class HangManPrinter {
    public static void printGameInfo(int gameId, String answer) {
        System.out.println(gameId + "번째 게임이 시작됩니다. 정답 단어는 " + answer.length() + "글자 입니다.");
    }

    public static void printGameRound(char[] guessedWord, int lives) {
        System.out.println("라운드 : " + new String(guessedWord) + ", 목숨 " + lives);
    }

    public static void printGameResult(boolean gameSuccess, int maxGameId, int gameId, int lives, String answer) {
        if (gameSuccess) {
            System.out.println("축하합니다. 정답입니다.\n");
        } else {
            System.out.println("목숨이 모두 소진되었습니다. 실패했습니다. 정답은 " + answer + "입니다.\n");
        }
        
        boolean nextGameExist = (gameId == maxGameId) ? false : true;

        if (nextGameExist) {
            System.out.println("다음 게임을 시작합니다.\n");
        } else {
            System.out.println("모든 게임이 종료되었습니다.\n");
        }
    }

    public static void printTotalResult(HashMap<Integer, String> gameResult, int selectGameId) {
        boolean isValidGameId = gameResult.containsKey(selectGameId);

        if (isValidGameId) {
            System.out.println(gameResult.get(selectGameId));
        } else {
            System.out.println("존재하지 않는 게임 id 입니다.");
        }
    }

    public static void printLog(HashMap<Integer, String[]> logHistory, int selectGameId, int selectRoundId) {
        try {
            System.out.println(logHistory.get(selectGameId)[selectRoundId - 1]);
        } catch (Exception e) {
            System.out.println("존재하지 않는 게임 id 또는 라운드 id 입니다.");
        }
    }
}