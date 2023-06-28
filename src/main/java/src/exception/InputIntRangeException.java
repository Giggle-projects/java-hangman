package src.exception;

import src.util.Message;

public class InputIntRangeException extends RuntimeException{
    public InputIntRangeException() {
        super(Message.ERR_MSG_INVALID_INPUT_INT_RANGE);
    }

    public InputIntRangeException(String message) {
        super(message);
    }
}
