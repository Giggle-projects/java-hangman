package src.exception;

import static src.util.InputHelper.*;

public enum ErrorCode {
	ERROR_INPUT_EMPTY("빈 입력입니다. 다시 입력해주세요."),
	ERROR_INVALID_INPUT_FORMAT("입력 형식이 올바르지 않습니다. 다시 입력해주세요."),
	ERROR_INVALID_INPUT_FORMAT_ALPHABET("입력 형식이 올바르지 않습니다. 알파벳 한 문자만 입력해주세요."),
	ERROR_INVALID_INPUT_COUNT("게임 횟수와 목숨이 제대로 입력되지 않았습니다. 다시 입력해주세요."),
	ERROR_INVALID_INPUT_DELIMITER("구분자가 올바르지 않습니다. "+INPUT_DELIMITER+ "기호로 구분하여 입력해주세요."),
	ERROR_DUPLICATE_ALPHABET_TRY("이미 시도한 알파벳 입니다. 다시 입력해주세요."),
	ERROR_NO_GAME_HISTORY("플레이 이력이 없습니다. 게임을 먼저 진행해주세요"),
	ERROR_NO_SUCH_ROUND_ID("id가 일치하는 라운드가 존재하지 않습니다. 다시 입력해주세요."),
	ERROR_NO_SUCH_GAME_ID("id가 일치하는 게임이 존재하지 않습니다. 다시 입력해주세요.");

	private final String message;

	ErrorCode(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
