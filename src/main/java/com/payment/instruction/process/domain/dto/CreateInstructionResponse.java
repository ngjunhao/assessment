package com.payment.instruction.process.domain.dto;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.payment.instruction.process.domain.enums.InstructionStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
public class CreateInstructionResponse {
    private String id;
    private String sourceSystem;
    private String payerAccount;
    private String payeeAccount;
    private BigDecimal amount;
    private String currency;
    private LocalDate requestedExecutionDate;
    private InstructionStatus status;
    private String requestedRail;
    private Instant createdAt;
    @JsonUnwrapped
    private ApiResult result;
}
