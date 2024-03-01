package com.example.tinkoffhomework.controller;

import com.example.tinkoffhomework.dto.ImageDto;
import com.example.tinkoffhomework.exception.ImageNotFoundException;
import com.example.tinkoffhomework.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/image")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @PostMapping(value = "/load")
    public ImageDto loadImage(MultipartFile file) throws Exception {
        System.out.println(file.getSize() + file.getName());
        return imageService.uploadImage(file);
    }

    @GetMapping(value = "/{link}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getImage(@PathVariable String link) throws Exception {
        return imageService.getImage(link);
    }

    @GetMapping(value = "/{id}/meta")
    public ImageDto getImageMeta(@PathVariable int id) throws ImageNotFoundException {
        return imageService.getImageMeta(id);
    }

}
