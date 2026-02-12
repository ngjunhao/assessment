package com.payment.instruction.process.domain.exception;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public  class ValidationException extends RuntimeException {
    private final String field;

    public ValidationException(String field, String message) {
        super(field + ": " + message);
        this.field = field;
    }
}