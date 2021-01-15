package com.example.student.api.exception;

import java.time.OffsetDateTime;

public class Exception {
    private int status;
    private OffsetDateTime dateException;
    private String message;

    public Exception(int status, OffsetDateTime dateException, String message) {
        this.status = status;
        this.dateException = dateException;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public OffsetDateTime getDateException() {
        return dateException;
    }

    public String getMessage() {
        return message;
    }
}
