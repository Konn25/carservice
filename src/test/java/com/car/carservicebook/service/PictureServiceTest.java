package com.car.carservicebook.service;

import com.car.carservicebook.jpa.Car;
import com.car.carservicebook.jpa.Picture;
import com.car.carservicebook.jpa.PictureRepository;
import com.car.carservicebook.jpa.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PictureServiceTest {

    @Mock
    private PictureRepository pictureRepository;

    @InjectMocks
    private PictureService pictureService;

    private final Car newCar = new Car();

    private final Picture newPicture = new Picture();

    private MultipartFile file;

    @BeforeEach
    void setUp(){

        pictureService = new PictureService(pictureRepository);

        newCar.setId(1L);
        newCar.setUser(new User());
        newCar.setFuel("Petrol");
        newCar.setMotor(1.7);
        newCar.setKilometer(123456);
        newCar.setManufacturer("Test");
        newCar.setPictureList(new ArrayList<>());
        newCar.setPrice(1237777);
        newCar.setRefuelingList(new ArrayList<>());
        newCar.setRepairList(new ArrayList<>());
        newCar.setType("Tester");
        newCar.setYear(1998);

        newPicture.setId(1L);
        newPicture.setType("image/png");
        newPicture.setName("test.png");
        newPicture.setImageData(null);
        newPicture.setCar(newCar);

        newCar.setPictureList(List.of(newPicture));

        file = new MockMultipartFile(newPicture.getName(), newPicture.getName(),
                                     newPicture.getType(), newPicture.getImageData());

    }

    @Test
    void getPictureById() {

        //GIVEN
        Optional<Picture> optionalPicture = Optional.of(newPicture);

        //WHEN
        when(pictureRepository.findPictureById(1L)).thenReturn(optionalPicture);

        //THEN
        assertEquals(optionalPicture,pictureService.getPictureById(1L));

    }

    @Test
    void deletePictureById() {

        //GIVEN
        //WHEN
        when(pictureRepository.findOnePictureById(1L)).thenReturn(Optional.of(newPicture));

        //THEN
        pictureService.deletePictureById(1L);

        verify(pictureRepository, times(1)).deleteById(1L);

    }

    @Test
    void createNewPicture() throws IOException {

        //GIVEN
        //WHEN
        pictureService.createNewPicture(file, 1L);

        //THEN
        verify(pictureRepository).save(any(Picture.class));

    }

    @Test
    void getCarAllPicture() {

        //GIVEN
        List<Picture> pictureList = List.of(newPicture);

        //WHEN
        when(pictureRepository.findPictureByCarId(1L)).thenReturn(pictureList);

        //THEN
        assertEquals(pictureList, pictureService.getCarAllPicture(1L));

    }

}