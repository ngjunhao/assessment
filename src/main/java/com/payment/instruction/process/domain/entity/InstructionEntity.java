package com.payment.instruction.process.domain.entity;

import com.payment.instruction.process.domain.enums.InstructionStatus;
import com.payment.instruction.process.repository.InstructionKey;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(
        name = "payment_instruction"
)
@Getter
@Setter
@AllArgsConstructor
public class InstructionEntity {

    @EmbeddedId
    private InstructionKey id;

    @Column(name = "payer_account", nullable = false, updatable = false, length = 64)
    private String payerAccount;

    @Column(name = "payee_account", nullable = false, updatable = false, length = 64)
    private String payeeAccount;

    @Column(name = "amount", nullable = false, updatable = false, precision = 19, scale = 4)
    private BigDecimal amount;

    @Column(name = "currency", nullable = false, updatable = false, length = 3)
    private String currency;

    @Column(name = "requested_execution_date", updatable = false, nullable = false)
    private LocalDate requestedExecutionDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 32)
    private InstructionStatus status;

    /*
     * Routing / rail transparency
     * e.g. FAST, PayNow, SWIFT, SEPA, etc.
     */
    @Column(name = "requested_rail", nullable = false, updatable = false, length = 32)
    private String requestedRail;

    /*
     * Audit & lifecycle observability
     */
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    protected InstructionEntity() {
        // JPA
    }
}