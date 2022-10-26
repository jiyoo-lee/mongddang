package com.jeeyulee.mongddang.common.result;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice
public class ResultExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResultException.class)
    public final ResponseEntity<ResultDTO> handleResultException(Exception e) {
        return ResponseEntity.ok(new ResultDTO(false, e.getMessage()));
    }
}
