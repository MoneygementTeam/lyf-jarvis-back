package com.moneygement.lyf.jarvis.common.error;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.NotReadablePropertyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@ControllerAdvice
public class GlobalExceptionAdvice {
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<Object> handleAllExceptions(Throwable e, WebRequest request) {
        log.error("Unknown exception occurred!", e);
        return new ResponseEntity<>(new ErrorResponse (e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundExceptions(Throwable e, WebRequest request) {
        log.error("EntityNotFoundException exception occurred!", e);
        return new ResponseEntity<>(new ErrorResponse (e.getMessage(), HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<Object> handleHttpServerErrorExceptions(HttpServerErrorException e, WebRequest request) {
        log.error("HttpServerError exception occurred!", e);
        return new ResponseEntity<>(new ErrorResponse (e.getMessage(), HttpStatus.BAD_GATEWAY), HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<Object> handleHttpClientErrorExceptions(HttpClientErrorException e, WebRequest request) {
        log.error("HttpClientError exception occurred! {}", e.getMessage());
        return new ResponseEntity<>(new ErrorResponse (e.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Object> handleResponseStatusException(ResponseStatusException e, WebRequest request) {
        log.error("ResponseStatus exception occurred! {}", e.getMessage());
        return new ResponseEntity<>(new ErrorResponse (e.getMessage(), HttpStatus.BAD_REQUEST), e.getStatusCode());
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<Object> handleBindException(BindException e, WebRequest request) {
        log.error("Data binding exception occurred! {}", e.getMessage());
        return new ResponseEntity<>(new ErrorResponse (e.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, WebRequest request) {
        log.error("Validation failed for method parameters annotated! {}", e.getMessage());
        return new ResponseEntity<>(new ErrorResponse (e.getMessage(), HttpStatus.BAD_REQUEST),  HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotReadablePropertyException.class)
    public ResponseEntity<Object> handleNotReadablePropertyException(NotReadablePropertyException e, WebRequest request) {
        log.error("Unreadable exception occurred! {}", e.getMessage());
        return new ResponseEntity<>(new ErrorResponse (e.getMessage(), HttpStatus.BAD_REQUEST),  HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleNotReadablePropertyException(RuntimeException e, WebRequest request) {
        log.error("Unreadable exception occurred! {}", e.getMessage());
        return new ResponseEntity<>(new ErrorResponse (e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR),  HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
