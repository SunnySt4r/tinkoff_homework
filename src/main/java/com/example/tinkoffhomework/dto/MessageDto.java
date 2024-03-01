package com.example.tinkoffhomework.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto implements Serializable {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("author")
    private String author;

    @JsonProperty("text")
    private String text;
}