package com.jeeyulee.mongddang.common.result;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@RestController
@ControllerAdvice
public class ResultExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResultException.class)
    public final ResponseEntity<ResultDTO> handleResultException(Exception e) {
        return ResponseEntity.ok(new ResultDTO(false, e.getMessage()));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        StringBuilder errorMessageBuilder = new StringBuilder();

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            errorMessageBuilder.append(fieldError.getDefaultMessage() + ", \n");
        }

        String errorMessage = errorMessageBuilder.substring(0, errorMessageBuilder.length() - 3);

        return ResponseEntity.ok(new ResultDTO(false, errorMessage));
    }
}
