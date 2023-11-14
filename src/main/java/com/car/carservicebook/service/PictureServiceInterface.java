package com.car.carservicebook.service;

import com.car.carservicebook.jpa.Picture;

import java.util.List;

public interface PictureServiceInterface {

    List<Picture> getPictureById(Long id);

    void deletePictureById(Long id);

    Picture createNewPicture(Picture picture);

}
