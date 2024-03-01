package com.example.tinkoffhomework.mapper;

import com.example.tinkoffhomework.domain.Image;
import com.example.tinkoffhomework.dto.ImageDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImageMapper {
    Image toEntity(ImageDto imageDto);
    ImageDto toImageDto(Image image);
}
