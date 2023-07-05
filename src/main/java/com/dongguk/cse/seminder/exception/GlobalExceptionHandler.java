package com.dongguk.cse.seminder.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {CoolSMSException.class})
    public void handleApiException(CoolSMSException e) {
        log.error("HandleApiException throw CoolSMSException : {}", e.getErrorCode());
    }

    @ExceptionHandler(value = {JsonParsingException.class})
    public void handleApiException(JsonParsingException e) {
        log.error("HandleApiException throw JsonParsingException : {}", e.getErrorCode());
    }

    @ExceptionHandler(value = {Exception.class})
    public void handleException(Exception e) {
        log.error("HandleException throw Exception : {}", e.getMessage());
    }
}
