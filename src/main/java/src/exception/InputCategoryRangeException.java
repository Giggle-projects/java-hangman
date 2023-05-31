package src.exception;

import src.util.Message;

public class InputCategoryRangeException extends RuntimeException{
    public InputCategoryRangeException() {
        super(Message.ERR_MSG_INVALID_INPUT_CATEGORY_RANGE);
    }

    public InputCategoryRangeException(String message) {
        super(message);
    }
}
