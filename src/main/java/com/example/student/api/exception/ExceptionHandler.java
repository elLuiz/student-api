package com.example.student.api.exception;

import com.example.student.domain.exception.ClientException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(ClientException.class)
    public ResponseEntity<Object> handleClientException(ClientException clientException, WebRequest request){
        HttpStatus status = HttpStatus.resolve(clientException.getCode());
        Exception exception = new Exception(status.value(), OffsetDateTime.now(), clientException.getMessage());
        return handleExceptionInternal(clientException, exception, new HttpHeaders(), status, request);
    }
}
