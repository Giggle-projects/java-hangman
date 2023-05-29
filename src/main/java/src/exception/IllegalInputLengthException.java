package src.exception;

import src.util.Message;

public class IllegalInputLengthException extends RuntimeException {

    public IllegalInputLengthException() {
        super(Message.ERR_INPUT_RANGE);
    }

    public IllegalInputLengthException(String msg) {
        super(msg);
    }
}
