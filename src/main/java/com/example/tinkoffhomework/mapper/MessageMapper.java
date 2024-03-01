package com.example.tinkoffhomework.mapper;

import com.example.tinkoffhomework.domain.Image;
import com.example.tinkoffhomework.domain.Message;
import com.example.tinkoffhomework.dto.MessageDto;
import com.example.tinkoffhomework.dto.MessageSendDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MessageMapper {
    MessageDto toMessageDto(Message message);
    List<MessageDto> toMessageDtos(List<Message> message);

    @Mapping(target = "images", source = "images")
    Message toEntity(MessageSendDto messageSendDto, List<Image> images);
}