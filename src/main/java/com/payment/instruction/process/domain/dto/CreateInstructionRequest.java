package com.payment.instruction.process.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class CreateInstructionRequest {
    private String id;
    private String sourceSystem;
    private String payerAccount;
    private String payeeAccount;
    private BigDecimal amount;
    private String currency;
    private LocalDate requestedExecutionDate;
    private String requestedRail;
}
