package com.programmingwizzard.lvlup;

public class APIException extends RuntimeException {

    public APIException(String message) {
        super(message);
    }

    public APIException(Throwable throwable) {
        super(throwable);
    }

    public APIException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
