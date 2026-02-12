package com.payment.instruction.process.domain.dto;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.payment.instruction.process.domain.enums.InstructionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class InstructionStatusResponse {

    private String id;
    private String sourceSystem;
    private InstructionStatus status;
    @JsonUnwrapped
    private ApiResult result;
}
