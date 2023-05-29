package src.exception;

import static src.exception.ErrorCode.*;

public class InputEmptyException extends RuntimeException{
	public InputEmptyException() {
		super(INPUT_EMPTY.getMessage());
	}
}
