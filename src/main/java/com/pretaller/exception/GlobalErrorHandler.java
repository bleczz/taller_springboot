package com.pretaller.exception;

import com.pretaller.responses.GeneralResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice // this annotation is used to handle exceptions globally
@Slf4j
public class GlobalErrorHandler {
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<GeneralResponse> notFoundExceptionHandler(NoResourceFoundException e){
        return GeneralResponse.getResponse(HttpStatus.NOT_FOUND, e.getMessage());
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GeneralResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getFieldError().getDefaultMessage();
        return GeneralResponse.getResponse(HttpStatus.BAD_REQUEST, errorMessage);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<GeneralResponse> handleUserNotFoundException(UserNotFoundException e){
        return GeneralResponse.getResponse(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GeneralResponse> generalExceptionHandler(Exception e){
        log.error(e.getMessage());
        log.error(e.getClass().toGenericString());
        // the stacktrace could also be printed if needed
        return GeneralResponse.getResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }





}