package com.payment.instruction.process.domain.mapper;


import com.payment.instruction.process.domain.dto.CreateInstructionRequest;
import com.payment.instruction.process.domain.dto.CreateInstructionResponse;
import com.payment.instruction.process.domain.dto.InstructionStatusResponse;
import com.payment.instruction.process.domain.entity.InstructionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface InstructionMapper {

    InstructionMapper INSTANCE = Mappers.getMapper(InstructionMapper.class);

    @Mapping(target = "id.instructionId", source = "id")
    @Mapping(target = "id.sourceSystem", source = "sourceSystem")
    InstructionEntity toEntity(CreateInstructionRequest dto);


    @Mapping(target = "id", source = "id.instructionId")
    @Mapping(target = "sourceSystem", source = "id.sourceSystem")
    CreateInstructionResponse toCreateResponse(InstructionEntity entity) ;

    @Mapping(target = "id", source = "id.instructionId")
    @Mapping(target = "sourceSystem", source = "id.sourceSystem")
    InstructionStatusResponse toGetStatusResponse(InstructionEntity entity);

}
