package com.ghosttech.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
@Getter
@Setter
public class RequestValidationException extends RuntimeException {
    private String errorCode;
    public RequestValidationException(String message,String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
