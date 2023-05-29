package src.exception;

import static src.exception.ErrorCode.*;

public class InvalidInputFormatException extends RuntimeException{
	public InvalidInputFormatException() {
		super(INVALID_INPUT_FORMAT.getMessage());
	}

	public InvalidInputFormatException(String message) {
		super(message);
	}
}
