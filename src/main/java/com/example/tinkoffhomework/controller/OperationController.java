package com.example.tinkoffhomework.controller;

import com.example.tinkoffhomework.domain.Operation.OperationType;
import com.example.tinkoffhomework.dto.OperationDto;
import com.example.tinkoffhomework.service.OperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/operation")
public class OperationController {

    private final OperationService operationService;

    @GetMapping("/{type}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<OperationDto> getOperations(@PathVariable OperationType type){
        return operationService.getOperationsByType(type);
    }
}
