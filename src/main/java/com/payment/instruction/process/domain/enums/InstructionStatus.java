package com.payment.instruction.process.domain.enums;

public enum InstructionStatus {
    PENDING,
    VALIDATION_FAILED,
    PROCESSING,
    COMPLETED,
    REJECTED,
    FAILED,
    CANCELLED
}