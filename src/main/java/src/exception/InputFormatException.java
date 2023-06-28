package src.exception;

import src.util.Message;

public class InputFormatException extends RuntimeException{

    public InputFormatException() {
        super(Message.ERR_MSG_INVALID_INPUT_FORMAT);
    }

    public InputFormatException(String message) {
        super(message);
    }
}
