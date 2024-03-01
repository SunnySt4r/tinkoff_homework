package com.example.tinkoffhomework.mapper;

import com.example.tinkoffhomework.domain.Operation;
import com.example.tinkoffhomework.dto.OperationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OperationMapper {
    @Mapping(target = "id", expression = "java(null)")
    Operation toEntity(OperationDto operationDto);
    OperationDto toOperationDto(Operation operation);
    List<OperationDto> toOperationDtos(List<Operation> operations);
}
