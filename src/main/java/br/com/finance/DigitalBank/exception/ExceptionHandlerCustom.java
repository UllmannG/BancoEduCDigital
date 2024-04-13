package br.com.finance.DigitalBank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandlerCustom {
    @ExceptionHandler(ExceptionMessage.class)
    public ResponseEntity<ErrorMessage> handlerExceptionMessage(ExceptionMessage ex) {
        ErrorMessage errorMessage = new ErrorMessage(ex.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
