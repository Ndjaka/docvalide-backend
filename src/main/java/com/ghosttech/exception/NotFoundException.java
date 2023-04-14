package com.ghosttech.exception;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class NotFoundException extends RuntimeException {
    private String errorCode;
    public NotFoundException(String message,String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}