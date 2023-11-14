package com.car.carservicebook.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PictureRepository extends JpaRepository<Picture, Long> {

    List<Picture> findPictureById(Long id);

    Optional<Picture> findOnePictureById(Long id);

}
