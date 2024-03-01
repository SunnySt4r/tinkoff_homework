package com.example.tinkoffhomework.exception;


import org.springframework.data.crossstore.ChangeSetPersister;

public class ImageNotFoundException extends NotFoundException {
    public ImageNotFoundException(String link) {
        super(link);
    }
}
