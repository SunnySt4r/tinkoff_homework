package com.example.tinkoffhomework.service;

import com.example.tinkoffhomework.dao.MessageRepository;
import com.example.tinkoffhomework.domain.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    public List<Message> getAllMessages() {
        return null;
    }

    public Message getMessageById(int id) {
        return messageRepository.findById(id).get();
    }

    public Message addMessage(String author, String text) {
        Message message = new Message();
        message.setAuthor(author);
        message.setText(text);
        return messageRepository.save(message);
    }
}
