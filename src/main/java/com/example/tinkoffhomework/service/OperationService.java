package com.example.tinkoffhomework.service;

import com.example.tinkoffhomework.dao.OperationRepository;
import com.example.tinkoffhomework.domain.Operation.OperationType;
import com.example.tinkoffhomework.dto.OperationDto;
import com.example.tinkoffhomework.mapper.OperationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OperationService {

    private final OperationRepository operationRepository;
    private final OperationMapper operationMapper;

    public void logOperation(OperationDto operationDto) {
        operationRepository.save(operationMapper.toEntity(operationDto));
    }

    public List<OperationDto> getOperationsByType(OperationType type){
        return operationRepository.findAllByType(type).stream().map(operationMapper::toOperationDto).toList();
    }
}
