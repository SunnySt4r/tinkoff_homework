package com.example.tinkoffhomework.domain;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Operation {

    @Id
    private String id;
    private String content;
    private LocalDateTime date;
    private OperationType type;

    public enum OperationType{
        WRITE, READ;
    }
}
