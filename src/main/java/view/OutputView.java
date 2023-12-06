package view;

import dto.GameStatusDto;
import dto.newGameDto;

public class OutputView {

    private static final String OUTPUT_PREFIX = "OU : ";
    private static final String PRINT_GAME_INFO_FORMAT = "%d번째 게임이 시작됩니다. 정답 단어는 %d글자 입니다.\n";
    private static final String PRINT_GAME_ROUND_FORMAT = "%d 라운드 : %s, 목숨 %d\n";
    private static final String MISMATCH_WORD = "_";

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
}
