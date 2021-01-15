package com.example.student.domain.exception;

public class ClientException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private int code;

    public ClientException(String message){
        super(message);
    }

    public ClientException(String message, int code) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
