package com.example.tinkoffhomework.dao;

import com.example.tinkoffhomework.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    boolean existsByLink(String link);
    boolean existsImagesByIdIn(List<Long> ids);
    Optional<Image> findById(long id);
    List<Image> findAllByIdIn(List<Long> ids);
}
