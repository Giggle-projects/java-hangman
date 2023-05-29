package src.exception;

import static src.exception.ErrorCode.*;

public class InvalidDelimiterException extends RuntimeException{
	public InvalidDelimiterException() {
		super(INVALID_INPUT_DELIMITER.getMessage());
	}
}
