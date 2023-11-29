package com.car.carservicebook.service;

import com.car.carservicebook.image.ImageUtil;
import com.car.carservicebook.jpa.Car;
import com.car.carservicebook.jpa.Picture;
import com.car.carservicebook.jpa.PictureRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PictureService implements PictureServiceInterface{

    private final PictureRepository pictureRepository;

    @Override
    @Transactional
    public Optional<Picture> getPictureById(Long id) {
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
    public void createNewPicture(MultipartFile file, Long carId) throws IOException {

        Car newCar = new Car();

        newCar.setId(carId);

        pictureRepository.save(Picture.builder().car(newCar)
                                      .name(file.getOriginalFilename())
                                      .type(file.getContentType())
                                      .imageData(ImageUtil.compressImage(file.getBytes())).build());

    }

    @Override
    public List<Picture> getCarAllPicture(Long carId) {
        return pictureRepository.findPictureByCarId(carId);
    }

    @Transactional
    public Picture getInfoImageByIdAndName(Long id, String name) {
        Optional<Picture> dbImage = pictureRepository.findByIdAndName(id,name);

        return Picture.builder()
                      .name(dbImage.get().getName())
                      .type(dbImage.get().getType())
                      .imageData(ImageUtil.decompressImage(dbImage.get().getImageData())).build();

    }

    @Transactional
    public byte[] getImage(Long id, String name) {
        Optional<Picture> dbImage = pictureRepository.findByIdAndName(id, name);
        return ImageUtil.decompressImage(dbImage.get().getImageData());
    }
}
