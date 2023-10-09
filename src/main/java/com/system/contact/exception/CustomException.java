package com.system.contact.exception;

public class CustomException extends Exception{

    private String enMessage;
    private int errorCode;

    public CustomException(String message, Throwable cause, String enMessage, int errorCode) {
        super(message, cause);
        this.enMessage = enMessage;
        this.errorCode = errorCode;
    }

    public CustomException(String message, String enMessage, int errorCode) {
        super(message);
        this.enMessage = enMessage;
        this.errorCode = errorCode;
    }

    public String getEnMessage() {
        return enMessage;
    }

    public void setEnMessage(String enMessage) {
        this.enMessage = enMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
