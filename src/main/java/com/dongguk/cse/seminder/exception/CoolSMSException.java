package com.dongguk.cse.seminder.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
public class CoolSMSException extends RuntimeException {
    private ErrorCode errorCode;
    private String message;
}
