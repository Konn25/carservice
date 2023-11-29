package com.car.carservicebook.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PictureRepository extends JpaRepository<Picture, Long> {

    Optional<Picture> findPictureById(Long id);

    Optional<Picture> findOnePictureById(Long id);

    Optional<Picture> findByIdAndName(Long id,String name);

    List<Picture> findPictureByCarId(Long id);

}
