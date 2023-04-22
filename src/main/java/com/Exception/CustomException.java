package com.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason = "这是自定义异常",value = HttpStatus.BAD_REQUEST)
public class CustomException extends RuntimeException{
    public CustomException() {
    }

    public CustomException(String message) {
        super(message);
    }
}
