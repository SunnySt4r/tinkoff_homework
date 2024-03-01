package com.example.tinkoffhomework.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImageDto implements Serializable {
    private String name;
    private long size;
    private String link;
}
