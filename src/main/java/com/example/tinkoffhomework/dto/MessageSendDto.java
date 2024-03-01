package com.example.tinkoffhomework.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MessageSendDto {
    private String author;
    private String text;
    private final LocalDateTime lastModifiedDate = LocalDateTime.now();

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Long> imagesId;
}
