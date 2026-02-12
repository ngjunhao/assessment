package com.payment.instruction.process.controller;

import com.payment.instruction.process.domain.dto.ApiResult;
import com.payment.instruction.process.domain.dto.CreateInstructionRequest;
import com.payment.instruction.process.domain.dto.CreateInstructionResponse;
import com.payment.instruction.process.domain.dto.InstructionStatusResponse;
import com.payment.instruction.process.service.InstructionService;
import com.payment.instruction.process.validator.InstructionIntakeValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/instruction")
@RequiredArgsConstructor
public class InstructionController {

    private final InstructionService instructionService;
    private final InstructionIntakeValidator validator;

    @GetMapping("/{sourceSystem}/{id}/status")
    public ResponseEntity<InstructionStatusResponse> getInstructionStatus(@PathVariable String sourceSystem, @PathVariable String id) {

        InstructionStatusResponse responseDTO = instructionService.getStatus(sourceSystem, id);
        responseDTO.setResult(new ApiResult());
        responseDTO.getResult().setSuccess(true);
        responseDTO.getResult().setMessage("Payment instruction status found");
        responseDTO.getResult().setErrorCode("");
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @PostMapping("/create")
    public ResponseEntity<CreateInstructionResponse> createInstruction(@RequestBody CreateInstructionRequest input) {

        validator.validate(input);
        CreateInstructionResponse responseDTO = instructionService.createInstruction(input);
        responseDTO.setResult(new ApiResult());
        responseDTO.getResult().setSuccess(true);
        responseDTO.getResult().setMessage("Payment instruction created");
        responseDTO.getResult().setErrorCode("");
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

}
