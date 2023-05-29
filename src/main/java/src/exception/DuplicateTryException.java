package src.exception;

import static src.exception.ErrorCode.*;

public class DuplicateTryException extends RuntimeException{
	public DuplicateTryException() {
		super(DUPLICATE_ALPHABET_TRY.getMessage());
	}
}
