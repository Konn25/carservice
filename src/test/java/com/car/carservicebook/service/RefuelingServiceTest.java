package com.car.carservicebook.service;


import com.car.carservicebook.jpa.Car;
import com.car.carservicebook.jpa.Refueling;
import com.car.carservicebook.jpa.RefuelingRepository;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class RefuelingServiceTest {

    @Mock
    private RefuelingRepository refuelingRepository;

    @InjectMocks
    private RefuelingService refuelingService;

    private final Refueling newRefueling = new Refueling();

    private final Car newCar = new Car();

    @BeforeEach
    void setUp(){

        refuelingService = new RefuelingService(refuelingRepository);

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

        newRefueling.setId(1L);
        newRefueling.setCar(newCar);
        newRefueling.setDate("2024.01.12");
        newRefueling.setPrice(13543);
        newRefueling.setFuelQuantity(12.78);
        newRefueling.setKilometer(230544);

    }

    @Test
    void getRefuelingByCarId() {

        //GIVEN
        List<Refueling> refuelingList = List.of(newRefueling);

        //WHEN
        when(refuelingRepository.findRefuelingByCarId(1L)).thenReturn(refuelingList);

        //THEN
        assertEquals(refuelingList, refuelingService.getRefuelingByCarId(1L));

    }

    @Test
    void createNewRefueling() {

        //GIVEN
        //WHEN
        when(refuelingRepository.save(newRefueling)).thenReturn(newRefueling);

        //THEN
        assertEquals(newRefueling, refuelingService.createNewRefueling(newRefueling));

    }

    @Test
    void updateRefueling() {

        //GIVEN
        //WHEN
        when(refuelingRepository.save(any(Refueling.class))).thenReturn(newRefueling);

        //THEN
        Refueling updateRefueling = refuelingService.updateRefueling(newRefueling);

        assertNotNull(updateRefueling, "The result can't be null! ");
        assertEquals(newRefueling.getId(), updateRefueling.getId(), "The IDs must match!!");

        verify(refuelingRepository, times(1)).save(newRefueling);

    }

    @Test
    void deleteRefuelingExists() {

        //GIVEN
        //WHEN
        when(refuelingRepository.findById(1L)).thenReturn(Optional.of(newRefueling));

        //THEN
        refuelingService.deleteRefueling(1L);

        verify(refuelingRepository, times(1)).deleteById(1L);

    }

    @Test
    void deleteRefuelingNotExists() {

        //GIVEN
        //WHEN
        when(refuelingRepository.findById(2L)).thenReturn(Optional.empty());

        //THEN
        refuelingService.deleteRefueling(2L);

        verify(refuelingRepository, never()).deleteById(2L);

    }
}