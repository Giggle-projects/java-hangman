package src.exception;

import src.util.Message;

public class InputCharRangeException extends RuntimeException{
    public InputCharRangeException() {
        super(Message.ERR_MSG_INVALID_INPUT_CHAR_RANGE);
    }

    public InputCharRangeException(String message) {
        super(message);
    }
}
