package com.example.tinkoffhomework.dao;

import com.example.tinkoffhomework.domain.Operation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OperationRepository extends MongoRepository<Operation, String> {

    List<Operation> findAllByType(Operation.OperationType type);
}
