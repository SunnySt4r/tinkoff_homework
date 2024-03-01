package com.example.tinkoffhomework.dto;

import com.example.tinkoffhomework.domain.Operation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OperationDto {
    private String content;
    private LocalDateTime date;
    private Operation.OperationType type;
}
