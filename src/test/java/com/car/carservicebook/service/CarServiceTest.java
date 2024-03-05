package com.car.carservicebook.service;

import com.car.carservicebook.jpa.Car;
import com.car.carservicebook.jpa.CarRepository;
import com.car.carservicebook.jpa.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarServiceTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarService carService;

    private final Car newCar = new Car();

    private final Car newCar2 = new Car();

    private final User newUser = new User();


    @BeforeEach
    void setUp() {

        carService = new CarService(carRepository);

        newUser.setId(1L);
        newUser.setName("JavaTest");
        newUser.setEmail("test@test.com");
        newUser.setPassword("123");
        newUser.setNickName("Elekk");
        newUser.setCars(List.of(newCar));


        newCar.setId(1L);
        newCar.setUser(this.newUser);
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

        newCar2.setId(2L);
        newCar2.setUser(new User());
        newCar2.setFuel("Petrol");
        newCar2.setMotor(1.9);
        newCar2.setKilometer(435213);
        newCar2.setManufacturer("Java");
        newCar2.setPictureList(new ArrayList<>());
        newCar2.setPrice(22223333);
        newCar2.setRefuelingList(new ArrayList<>());
        newCar2.setRepairList(new ArrayList<>());
        newCar2.setType("Spring");
        newCar2.setYear(2005);

    }

    @Test
    void getUserAllCar() {

        //GIVEN
        List<Car> carList = List.of(this.newCar);

        //WHEN
        when(carRepository.findCarByUser_Id(1L)).thenReturn(carList);

        //THEN
        assertEquals(carList, carService.getUserAllCar(1L));

    }

    @Test
    void getCarById() {

        //GIVEN
        Optional<Car> carOptional = Optional.of(this.newCar);

        //WHEN
        when(carRepository.findCarById(1L)).thenReturn(carOptional);

        //THEN
        assertEquals(carOptional, carService.getCarById(1L));

    }

    @Test
    void createNewCar() {

        //GIVEN
        //WHEN
        when(carRepository.save(this.newCar)).thenReturn(this.newCar);

        //THEN
        assertEquals(this.newCar, carService.createNewCar(this.newCar));

    }

    @Test
    void updateCar() {

        //GIVEN
        //WHEN
        when(carRepository.findCarById(1L)).thenReturn(Optional.of(this.newCar));

        when(carRepository.save(any(Car.class))).thenReturn(this.newCar);

        //THEN
        Car updatedCar = carService.updateCar(newCar);

        verify(carRepository).save(newCar);

        assertEquals(1L,updatedCar.getId());
        assertEquals("Test", updatedCar.getManufacturer());
        assertEquals("Tester",updatedCar.getType());

    }

    @Test
    void deleteCarExists() {

        //GIVEN
        //WHEN
        when(carRepository.findCarById(1L)).thenReturn(Optional.of(this.newCar));

        //THEN
        carService.deleteCar(1L);

        verify(carRepository, times(1)).deleteById(1L);

    }

    @Test
    void deleteCarNotExists() {

        //GIVEN
        //WHEN
        when(carRepository.findCarById(2L)).thenReturn(Optional.empty());

        //THEN
        carService.deleteCar(2L);

        verify(carRepository, never()).deleteById(anyLong());

    }

}