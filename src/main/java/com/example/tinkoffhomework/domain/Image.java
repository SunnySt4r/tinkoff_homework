package com.example.tinkoffhomework.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "image")
public class Image {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "image_id_seq")
    @SequenceGenerator(name = "image_id_seq", sequenceName = "image_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "size")
    private Long size;

    @Column(name = "link")
    private String link;

    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(
            name = "messages_images",
            joinColumns = @JoinColumn(name = "image_id"),
            inverseJoinColumns = @JoinColumn(name = "message_id")
    )
    private List<Message> messages = new ArrayList<>();
}
