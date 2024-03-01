package com.example.tinkoffhomework.controller;


import com.example.tinkoffhomework.dto.ImageDto;
import com.example.tinkoffhomework.dto.MessageDto;
import com.example.tinkoffhomework.dto.MessageSendDto;
import com.example.tinkoffhomework.exception.ImagesNotFoundException;
import com.example.tinkoffhomework.exception.MessageNotFoundException;
import com.example.tinkoffhomework.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
public class MessageController{

    private final MessageService messageService;

    @GetMapping("/all")
    public List<MessageDto> getMessages() {
        return messageService.getAllMessages();
    }

    @GetMapping("/{id}")
    public MessageDto getMessage(@PathVariable long id) throws MessageNotFoundException {
        return messageService.getMessageById(id);
    }

    @PostMapping
    public MessageSendDto sendMessage(@RequestBody MessageSendDto messageSendDto) throws ImagesNotFoundException {
        return messageService.addMessage(messageSendDto);
    }

    @GetMapping("/{id}/image")
    public List<ImageDto> getMessageImages(@PathVariable long id) throws MessageNotFoundException {
        return messageService.getMessageImages(id);
    }

}
