package src.exception;

import static src.util.InputHelper.*;

public enum ErrorCode {
	INPUT_EMPTY("빈 입력입니다. 다시 입력해주세요."),
	INVALID_INPUT_FORMAT("입력 형식이 올바르지 않습니다. 다시 입력해주세요."),
	INVALID_INPUT_FORMAT_ALPHABET("입력 형식이 올바르지 않습니다. 알파벳 한 문자만 입력해주세요."),
	INVALID_INPUT_DELIMITER("구분자가 올바르지 않습니다. "+INPUT_DELIMITER+ "기호로 구분하여 입력해주세요.");

	private final String message;

	ErrorCode(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
