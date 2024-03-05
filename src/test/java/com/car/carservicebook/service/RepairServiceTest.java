package com.car.carservicebook.service;

import com.car.carservicebook.jpa.Car;
import com.car.carservicebook.jpa.Repair;
import com.car.carservicebook.jpa.RepairRepository;
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
class RepairServiceTest {

    @Mock
    private RepairRepository repairRepository;

    @InjectMocks
    private RepairService repairService;

    private final Repair newRepair = new Repair();

    private final Car newCar = new Car();

    @BeforeEach
    void setUp() {

        repairService = new RepairService(this.repairRepository);

        newCar.setId(1L);
        newCar.setUser(null);
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

        newRepair.setId(1L);
        newRepair.setRepair_id(1L);
        newRepair.setDate("2024.01.21");
        newRepair.setPrice(85600);
        newRepair.setCar(newCar);

    }

    @Test
    void getAllRepairByCarId() {

        //GIVEN
        List<Repair> repairList = List.of(newRepair);

        //WHEN
        when(repairRepository.findRepairByCarId(1L)).thenReturn(repairList);

        //THEN
        assertEquals(repairList,repairService.getAllRepairByCarId(1L));

    }

    @Test
    void createNewRepair() {

        //GIVEN
        //WHEN
        when(repairRepository.save(newRepair)).thenReturn(newRepair);

        //THEN
        assertEquals(newRepair,repairService.createNewRepair(newRepair));

    }

    @Test
    void deleteRepairExists() {

        //GIVEN
        //WHEN
        when(repairRepository.findById(1L)).thenReturn(Optional.of(newRepair));

        //THEN
        repairService.deleteRepair(1L);

        verify(repairRepository, times(1)).deleteById(1L);

    }

    @Test
    void deleteRepairNotExists() {

        //GIVEN
        //WHEN
        when(repairRepository.findById(2L)).thenReturn(Optional.empty());

        //THEN
        repairService.deleteRepair(2L);

        verify(repairRepository, never()).deleteById(2L);

    }

    @Test
    void updateRepair() {

        //GIVEN
        //WHEN
        when(repairRepository.save(any(Repair.class))).thenReturn(newRepair);

        //THEN
        Repair updateRepair = repairService.updateRepair(newRepair);

        assertNotNull(updateRepair, "The result can't be null!");
        assertEquals(newRepair.getId(), updateRepair.getId(), "The IDs must match");

        verify(repairRepository, times(1)).save(newRepair);

    }


    @Test
    void getRepairById() {

        //GIVEN
        //WHEN
        when(repairRepository.findRepairById(1L)).thenReturn(newRepair);

        //THEN
        assertEquals(newRepair, repairService.getRepairById(1L));

    }
}