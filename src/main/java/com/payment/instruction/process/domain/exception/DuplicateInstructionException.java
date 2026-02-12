package com.payment.instruction.process.domain.exception;

import com.payment.instruction.process.repository.InstructionKey;

public class DuplicateInstructionException extends RuntimeException {
    public DuplicateInstructionException(InstructionKey key) {
        super("Duplicate instruction: sourceSystem=" + key.getSourceSystem() + ", id=" + key.getInstructionId());
    }
}
