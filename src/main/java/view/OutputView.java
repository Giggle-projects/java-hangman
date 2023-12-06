package view;

import dto.GameStatusDto;
import dto.newGameDto;

public class OutputView {

    private static final String OUTPUT_PREFIX = "OU : ";
    private static final String PRINT_GAME_INFO_FORMAT = "%d번째 게임이 시작됩니다. 정답 단어는 %d글자 입니다.\n";
    private static final String PRINT_GAME_ROUND_FORMAT = "%d 라운드 : %s, 목숨 %d\n";
    private static final String MISMATCH_WORD = "_";

    private static final String MESSAGE_SUCCESS = "축하합니다. 정답입니다.\n";
    private static final String MESSAGE_FAILURE = "실패, 다시 도전해보세요!\n";

    private OutputView() {

    }

    public static void printGameInfo(newGameDto gameInitDto) {
        System.out.printf(OUTPUT_PREFIX + PRINT_GAME_INFO_FORMAT, gameInitDto.gameRound(), gameInitDto.wordLength());
    }

    public static void printGameStatus(GameStatusDto gameStatusDto) {
        System.out.printf(OUTPUT_PREFIX + PRINT_GAME_ROUND_FORMAT,
                gameStatusDto.getGameCount(),
                gameStatusDto.getWordDto().matchedWord(MISMATCH_WORD),
                gameStatusDto.getRemainingLife());
    }

    public static void printGameResult(boolean result) {
        String message = MESSAGE_FAILURE;
        if (result) {
            message = MESSAGE_SUCCESS;
        }
        System.out.println(message);
    }
}
