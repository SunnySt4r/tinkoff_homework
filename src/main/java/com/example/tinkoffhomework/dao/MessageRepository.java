package com.example.tinkoffhomework.dao;

import com.example.tinkoffhomework.domain.Message;
import org.springframework.data.repository.CrudRepository;


public interface MessageRepository extends CrudRepository<Message, Integer> {
}
