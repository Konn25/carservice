package com.car.carservicebook.service;

import com.car.carservicebook.jpa.RepairName;
import com.car.carservicebook.jpa.RepairNameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RepairNameServiceTest {
    @Mock
    private RepairNameRepository repairNameRepository;

    @InjectMocks
    private RepairNameService repairNameService;

    RepairName repairName = new RepairName();

    @BeforeEach
    void setUp(){

        repairNameService = new RepairNameService(repairNameRepository);

        repairName.setRepairName("Oil change");
        repairName.setId(1L);
        repairName.setRepair(null);

    }

    @Test
    void createNewRepairName() {

        //GIVEN
        //WHEN
        when(repairNameRepository.save(any(RepairName.class))).thenReturn(repairName);

        RepairName savedRepairName = repairNameService.createNewRepairName(repairName);

        //THEN
        assertNotNull(savedRepairName, "The result can't be null!");
        assertEquals(repairName.getId(), savedRepairName.getId(), "The IDs must match!");

        verify(repairNameRepository, times(1)).save(repairName);

    }

    @Test
    void getAllRepairName() {

        //GIVEN
        List<RepairName> repairNameList = List.of(repairName);

        //WHEN
        when(repairNameRepository.findAll()).thenReturn(repairNameList);

        //THEN
        assertEquals(repairNameList, repairNameService.getAllRepairName());

    }
}