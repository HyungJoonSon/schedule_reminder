package com.dongguk.cse.seminder.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    SMS_FAIL(HttpStatus.INTERNAL_SERVER_ERROR, "메세지 전송에 실패하였습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
