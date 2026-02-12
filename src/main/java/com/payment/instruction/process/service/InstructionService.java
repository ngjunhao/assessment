package com.payment.instruction.process.service;

import com.payment.instruction.process.domain.dto.CreateInstructionRequest;
import com.payment.instruction.process.domain.dto.CreateInstructionResponse;
import com.payment.instruction.process.domain.dto.InstructionStatusResponse;
import com.payment.instruction.process.domain.entity.InstructionEntity;
import com.payment.instruction.process.domain.enums.InstructionStatus;
import com.payment.instruction.process.domain.exception.DuplicateInstructionException;
import com.payment.instruction.process.domain.exception.InstructionNotFoundException;
import com.payment.instruction.process.domain.mapper.InstructionMapper;
import com.payment.instruction.process.repository.InstructionKey;
import com.payment.instruction.process.repository.InstructionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InstructionService {

    private final InstructionMapper mapper;
    private  final InstructionRepository repository;

    @Transactional
    public InstructionStatusResponse getStatus(String sourceSystem, String id) {

        InstructionEntity entity = repository
                .findOneById_InstructionIdAndId_SourceSystem(id, sourceSystem)
                .orElseThrow(() -> new InstructionNotFoundException(sourceSystem, id));

        InstructionStatusResponse responseDto = mapper.toGetStatusResponse(entity);

        return responseDto;

    }

    @Transactional
    public CreateInstructionResponse createInstruction(CreateInstructionRequest input) {

        InstructionEntity entity = mapper.toEntity(input);

        boolean recordExists = repository.existsById(entity.getId());
        if (recordExists) {
            throw new DuplicateInstructionException(entity.getId());
        }

        entity.setStatus(InstructionStatus.PENDING);
        entity.setCreatedAt(Instant.now());
        entity.setUpdatedAt(Instant.now());
        repository.save(entity);

        CreateInstructionResponse responseDto = mapper.toCreateResponse(entity);

        return responseDto;
    }
}



