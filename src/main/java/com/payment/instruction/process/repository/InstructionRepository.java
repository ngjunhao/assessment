package com.payment.instruction.process.repository;

import com.payment.instruction.process.domain.entity.InstructionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface InstructionRepository extends JpaRepository<InstructionEntity, InstructionKey> {
    Optional<InstructionEntity> findOneById_InstructionIdAndId_SourceSystem(String id, String sourceSystem);
}

