package view;

import dto.GameInitDto;

public class OutputView {

    private static final String OUTPUT_PREFIX = "OU : ";
    private static final String PRINT_GAME_INFO_FORMAT = "%d번째 게임이 시작됩니다. 정답 단어는 %d글자 입니다.\n";

    private OutputView() {

    }

    public static void printGameInfo(GameInitDto gameInitDto) {
        System.out.printf(OUTPUT_PREFIX + PRINT_GAME_INFO_FORMAT, gameInitDto.gameRound(), gameInitDto.wordLength());
    }
}
