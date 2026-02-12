package com.payment.instruction.process.domain.exception;

public class InstructionNotFoundException extends RuntimeException {
    public InstructionNotFoundException(String sourceSystem, String id) {
        super("Instruction not found: sourceSystem=" + sourceSystem + ", id=" + id);
    }
}
