package com.example.tinkoffhomework.resources;


import com.example.tinkoffhomework.domain.Message;
import com.example.tinkoffhomework.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MessageController{
    private final MessageService messageService;

    @QueryMapping
    public List<Message> getMessages() {
        return messageService.getAllMessages();
    }

    @QueryMapping
    public Message getMessage(@Argument int id) {
        return messageService.getMessageById(id);
    }

    @MutationMapping
    public Message sendMessage(@Argument String author, @Argument String text) {
        return messageService.addMessage(author, text);
    }
}
