package src.util;

public interface Message {

    String MSG_GAME_START = "게임 횟수와 목숨을 입력하세요.";
    String MSG_ROUND_CLEAR = "축하합니다. 정답입니다.";
    String MSG_ROUND_OVER = "주어진 목숨을 전부 사용하셨습니다.";
    String ERR_MSG_INVALID_INPUT_FORMAT = "잘못된 입력입니다. 게임 횟수와 목숨을 ','로 구분하여 다시 입력해주세요.";
    String ERR_MSG_INVALID_INPUT_NUMBER_FORMAT = "잘못된 입력입니다. 숫자로 다시 입력해주세요.";
    String ERR_MSG_INVALID_INPUT_CHAR_RANGE = "한 글자만 입력해주세요.";
    String ERR_MSG_INVALID_INPUT_CHAR_FORMAT = "a-z, A-Z 사이의 글자만 입력해주세요.";


}
