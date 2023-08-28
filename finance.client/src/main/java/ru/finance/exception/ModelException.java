package ru.finance.exception;

public class ModelException extends Exception{
    private final int code;

    public ModelException(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public TitleException getExMessage(){
        return TitleException.values()[this.getCode()];
    }
}
