package com.payment.instruction.process.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
public class InstructionKey implements Serializable {
    @Column(name = "id", nullable = false, updatable = false, length = 64)
    private String instructionId;

    @Column(name = "source_system", nullable = false, updatable = false, length = 64)
    private String sourceSystem;

    protected InstructionKey() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InstructionKey)) return false;
        InstructionKey that = (InstructionKey) o;
        return Objects.equals(instructionId, that.instructionId)
                && Objects.equals(sourceSystem, that.sourceSystem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(instructionId, sourceSystem);
    }
}
