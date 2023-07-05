package src.exception;

import src.util.Message;

public class InputMenuException extends RuntimeException {

    public InputMenuException() {
        super(Message.ERR_OUT_OF_MENU_BOUNDARY);
    }

    public InputMenuException(String msg) {
        super(msg);
    }
}
