package com.dongguk.cse.seminder.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JsonParsingException extends RuntimeException{
    private ErrorCode errorCode;
}
