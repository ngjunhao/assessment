package com.payment.instruction.process.advice;

import com.payment.instruction.process.controller.InstructionController;
import com.payment.instruction.process.domain.dto.ApiResult;
import com.payment.instruction.process.domain.exception.DuplicateInstructionException;
import com.payment.instruction.process.domain.exception.InstructionNotFoundException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = InstructionController.class)
public class InstructionControllerExceptionHandler {

    @ExceptionHandler(InstructionNotFoundException.class)
    public ResponseEntity<ApiResult> handleNotFound(RuntimeException ex) {
        ApiResult result = new ApiResult();
        result.setSuccess(false);
        result.setErrorCode("NOT_FOUND");
        result.setMessage(ex.getMessage());
        return ResponseEntity.status(404).body(result);
    }

    @ExceptionHandler(DuplicateInstructionException.class)
    public ResponseEntity<ApiResult> handleDuplicate(RuntimeException ex) {
        ApiResult result = new ApiResult();
        result.setSuccess(false);
        result.setErrorCode("DUPLICATE");
        result.setMessage(ex.getMessage());
        return ResponseEntity.status(409).body(result);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<?> handleDb(DataAccessException ex) {
        ApiResult result = new ApiResult();
        result.setSuccess(false);
        result.setErrorCode("DATABASE_UNAVAILABLE");
        result.setMessage(ex.getMessage());
        return ResponseEntity.status(503).body(result);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleUnexpected(Exception ex) {
        ApiResult result = new ApiResult();
        result.setSuccess(false);
        result.setErrorCode("OTHERS");
        result.setMessage(ex.getMessage());
        return ResponseEntity.status(500).body(result);
    }
}
