package com.example.tinkoffhomework.service;

import com.example.tinkoffhomework.dao.MessageRepository;
import com.example.tinkoffhomework.domain.Image;
import com.example.tinkoffhomework.domain.Message;
import com.example.tinkoffhomework.domain.Operation;
import com.example.tinkoffhomework.dto.ImageDto;
import com.example.tinkoffhomework.dto.MessageDto;
import com.example.tinkoffhomework.dto.MessageSendDto;
import com.example.tinkoffhomework.dto.OperationDto;
import com.example.tinkoffhomework.exception.ImagesNotFoundException;
import com.example.tinkoffhomework.exception.MessageNotFoundException;
import com.example.tinkoffhomework.mapper.ImageMapper;
import com.example.tinkoffhomework.mapper.MessageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    private final ImageMapper imageMapper;
    private final MessageMapper messageMapper;

    private final OperationService operationService;
    private final ImageService imageService;

    public List<MessageDto> getAllMessages() {
        return messageRepository.findAll().stream()
                .map(messageMapper::toMessageDto).toList();
    }

    @Cacheable(value = "MessageService::getMessageById", key = "#id")
    public MessageDto getMessageById(long id) throws MessageNotFoundException {
        Optional<Message> messageOptional = messageRepository.findById(id);
        if(messageOptional.isEmpty()){
            throw new MessageNotFoundException(String.valueOf(id));
        }
        MessageDto messageDto = messageMapper.toMessageDto(messageOptional.get());

        operationService.logOperation(
                new OperationDto(
                        String.format("Read message: %s", messageDto),
                        LocalDateTime.now(),
                        Operation.OperationType.READ
                )
        );

        return messageDto;
    }

    public MessageSendDto addMessage(MessageSendDto messageSendDto) throws ImagesNotFoundException {
        List<Long> imagesId = messageSendDto.getImagesId() != null ? messageSendDto.getImagesId() : List.of();

        if(!imagesId.isEmpty() && !imageService.existsAll(imagesId)){
            throw new ImagesNotFoundException("images");
        }

        messageRepository.save(messageMapper.toEntity(
                messageSendDto,
                imageService.getAllImages(imagesId)
        ));

        operationService.logOperation(
                new OperationDto(
                        String.format("Send message: %s", messageSendDto),
                        LocalDateTime.now(),
                        Operation.OperationType.WRITE
                )
        );

        return messageSendDto;
    }

    @Cacheable(value = "MessageService::getMessageImages", key = "#id + '.images'")
    public List<ImageDto> getMessageImages(long id) throws MessageNotFoundException {
        Optional<Message> messageOptional = messageRepository.findById(id);

        if(messageOptional.isEmpty()){
            throw new MessageNotFoundException(String.valueOf(id));
        }

        List<ImageDto> images = messageOptional.get().getImages()
                .stream().map(imageMapper::toImageDto).toList();

        operationService.logOperation(
                new OperationDto(
                        String.format("Read message images: %s", images),
                        LocalDateTime.now(),
                        Operation.OperationType.READ
                )
        );

        return images;
    }
}
