package src.exception;

import static src.exception.ErrorCode.*;

public class InvalidDelimiterException extends RuntimeException{
	public InvalidDelimiterException() {
		super(ERROR_INVALID_INPUT_DELIMITER.getMessage());
	}
}
