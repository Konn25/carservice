package com.car.carservicebook.controller;

import com.car.carservicebook.dto.RepairDTO;
import com.car.carservicebook.jpa.*;
import com.car.carservicebook.service.CarService;
import com.car.carservicebook.service.RepairService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@MockitoSettings(strictness = Strictness.LENIENT)
class RepairControllerTest {

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private CarRepository carRepository;

    @Mock
    private CarService carService;

    @Mock
    private RepairRepository repairRepository;

    @Mock
    private RepairService repairService;

    @InjectMocks
    private RepairController repairController;

    private final Repair repair = new Repair();

    private final Car newCar = new Car();

    @Autowired
    private MockMvc mockMvc;

    private final RepairDTO repairDTO = new RepairDTO();


    @BeforeEach
    void setUp() {

        mockMvc = MockMvcBuilders.standaloneSetup(repairController).build();

        repairService = new RepairService(repairRepository);

        carService = new CarService(carRepository);

        repairController = new RepairController(repairService,carService,modelMapper);

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

        repair.setId(1L);
        repair.setRepair_id(1L);
        repair.setDate("2024.01.23");
        repair.setPrice(23455);
        repair.setCar(newCar);

        repairDTO.setId(1L);
        repairDTO.setCar_id(1L);
        repairDTO.setRepair_id(1L);
        repairDTO.setDate("2024.01.23");
        repairDTO.setPrice(23455);

    }

    @Test
    void getAllRepairByCarId() throws Exception {

        //GIVEN

        List<Repair> repairList = List.of(repair);

        //WHEN
        when(repairService.getAllRepairByCarId(1L)).thenReturn(repairList);

        MvcResult result = mockMvc.perform(get("/v1/car/repair/{carId}", 1L)
                                .contentType(MediaType.APPLICATION_JSON)).andReturn();

        //THEN
        assertEquals(200, result.getResponse().getStatus());

    }

    @Test
    void createNewRepair() {

        //GIVEN
        given(modelMapper.map(repair, RepairDTO.class)).willReturn(repairDTO);
        given(modelMapper.map(repairDTO, Repair.class)).willReturn(repair);
        given(repairRepository.findAll()).willReturn(List.of(repair));
        given(repairService.createNewRepair(repair)).willReturn(repair);


        //WHEN
        ResponseEntity<String> actual = repairController.createNewRepair(repairDTO);

        //THEN
        assertEquals(HttpStatus.CREATED, actual.getStatusCode());

    }

    @Test
    void deleteRepairById() {

        //GIVEN
        given(repairRepository.findRepairById(1L)).willReturn(repair);

        //WHEN
        ResponseEntity<String> actual = repairController.deleteRepairById(1L);

        //THEN
        assertEquals(HttpStatus.OK, actual.getStatusCode());

    }

    @Test
    void updateRepair() {

        //GIVEN
        given(modelMapper.map(repair, RepairDTO.class)).willReturn(repairDTO);
        given(modelMapper.map(repairDTO, Repair.class)).willReturn(repair);
        given(repairRepository.findRepairById(1L)).willReturn(repair);
        given(carService.getCarById(1L)).willReturn(Optional.of(newCar));
        given(repairService.updateRepair(repair)).willReturn(repair);

        //WHEN
        ResponseEntity<String> actual = repairController.updateRepair(1L, repairDTO);

        //THEN
        assertEquals(HttpStatus.CREATED, actual.getStatusCode());

    }

    @Test
    void updateRepairFailed() {

        //GIVEN
        given(modelMapper.map(repair, RepairDTO.class)).willReturn(repairDTO);
        given(modelMapper.map(repairDTO, Repair.class)).willReturn(repair);
        given(repairRepository.findRepairById(1L)).willReturn(repair);
        given(repairService.updateRepair(repair)).willReturn(repair);

        //WHEN
        ResponseEntity<String> actual = repairController.updateRepair(1L, repairDTO);

        //THEN
        assertEquals(HttpStatus.BAD_REQUEST, actual.getStatusCode());

    }

}