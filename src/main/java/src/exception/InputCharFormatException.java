package src.exception;

import src.util.Message;

public class InputCharFormatException extends RuntimeException{

    public InputCharFormatException() {
        super(Message.ERR_MSG_INVALID_INPUT_CHAR_FORMAT);
    }

    public InputCharFormatException(String message) {
        super(message);
    }
}
