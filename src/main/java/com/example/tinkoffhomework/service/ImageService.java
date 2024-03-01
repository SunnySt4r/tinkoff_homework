package com.example.tinkoffhomework.service;

import com.example.tinkoffhomework.dao.ImageRepository;
import com.example.tinkoffhomework.domain.Image;
import com.example.tinkoffhomework.domain.Operation;
import com.example.tinkoffhomework.dto.ImageDto;
import com.example.tinkoffhomework.dto.OperationDto;
import com.example.tinkoffhomework.exception.ImageNotFoundException;
import com.example.tinkoffhomework.mapper.ImageMapper;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;
    private final ImageMapper imageMapper;

    private final OperationService operationService;
    private final MinioService minioService;

    @Cacheable(value = "ImageService::getImageMeta", key = "#file.originalFilename")
    public ImageDto uploadImage(MultipartFile file) throws Exception{
        System.out.println(file.getSize() + file.getName());
        ImageDto imageDto = minioService.uploadImage(file);
        imageRepository.save(imageMapper.toEntity(imageDto));
        System.out.println(file.getSize() + file.getName());
        operationService.logOperation(
                new OperationDto(
                        String.format("Upload image: %s", imageDto),
                        LocalDateTime.now(),
                        Operation.OperationType.WRITE
                )
        );
        System.out.println(file.getSize() + file.getName());
        return imageDto;
    }

    public byte[] getImage(String link) throws Exception{
        if (!imageRepository.existsByLink(link)){
            throw new ImageNotFoundException(link);
        }

        return minioService.downloadImage(link);
    }

    @Cacheable(value = "ImageService::getImageMeta", key = "#id")
    public ImageDto getImageMeta(long id) throws ImageNotFoundException {
        Optional<Image> imageOptional = imageRepository.findById(id);

        if (imageOptional.isEmpty()){
            throw new ImageNotFoundException(String.valueOf(id));
        }

        ImageDto imageDto = imageMapper.toImageDto(imageOptional.get());

        operationService.logOperation(
                new OperationDto(
                        String.format("Read image metadata: %s", imageDto),
                        LocalDateTime.now(),
                        Operation.OperationType.READ
                )
        );

        return imageDto;
    }

    public boolean existsAll(List<Long> imagesId){
        return imageRepository.existsImagesByIdIn(imagesId);
    }

    public List<Image> getAllImages(List<Long> imagesId){
        return imageRepository.findAllByIdIn(imagesId);
    }
}
