package com.example.tinkoffhomework.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "message")
public class Message {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "message_id_seq")
    @SequenceGenerator(name = "message_id_seq", sequenceName = "message_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "text")
    private String text;

    @Column(name = "author")
    private String author;

    @Column(name = "last_modified")
    private LocalDateTime lastModifiedDate;

    @ManyToMany
    @JoinTable(
            name = "messages_images",
            joinColumns = @JoinColumn(name = "message_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id")
    )
    private List<Image> images = new ArrayList<>();
}
