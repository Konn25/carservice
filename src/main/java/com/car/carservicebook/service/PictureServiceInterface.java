package com.car.carservicebook.service;

import com.car.carservicebook.jpa.Picture;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface PictureServiceInterface {

    Optional<Picture> getPictureById(Long id);

    void deletePictureById(Long id);

    void createNewPicture(MultipartFile file, Long carId) throws IOException;

    List<Picture> getCarAllPicture(Long carId);

}
