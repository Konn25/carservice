package com.car.carservicebook.service;

import com.car.carservicebook.jpa.Picture;
import com.car.carservicebook.jpa.PictureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PictureService implements PictureServiceInterface{

    private final PictureRepository pictureRepository;

    @Override
    public List<Picture> getPictureById(Long id) {
        return pictureRepository.findPictureById(id);
    }

    @Override
    public void deletePictureById(Long id) {
        Optional<Picture> foundPicture = pictureRepository.findOnePictureById(id);

        if(foundPicture.isPresent() && foundPicture.get().getId()>0){
            pictureRepository.deleteById(id);
        }
    }

    @Override
    public Picture createNewPicture(Picture picture) {
        return pictureRepository.save(picture);
    }
}
