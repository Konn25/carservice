package com.car.carservicebook.controller;

import com.car.carservicebook.dto.RefuelingDTO;
import com.car.carservicebook.jpa.*;
import com.car.carservicebook.service.CarService;
import com.car.carservicebook.service.RefuelingService;
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

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@MockitoSettings(strictness = Strictness.LENIENT)
class RefuelingControllerTest {

    @Mock
    private ModelMapper modelMapper;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private CarRepository carRepository;

    @Mock
    private CarService carService;

    @Mock
    private RefuelingRepository refuelingRepository;

    @Mock
    private RefuelingService refuelingService;

    @InjectMocks
    private RefuelingController refuelingController;


    private final Refueling refueling = new Refueling();

    private final RefuelingDTO refuelingDTO = new RefuelingDTO();


    @BeforeEach
    void setUp() {

        mockMvc = MockMvcBuilders.standaloneSetup(refuelingController).build();

        carService = new CarService(carRepository);

        refuelingService = new RefuelingService(refuelingRepository);

        refuelingController = new RefuelingController(refuelingService, carService, modelMapper);

        refueling.setId(1L);
        refueling.setDate("");
        refueling.setPrice(234444);
        refueling.setKilometer(231111);
        refueling.setFuelQuantity(25.4);
        refueling.setCar(new Car());

        refuelingDTO.setId(1L);
        refuelingDTO.setDate("");
        refuelingDTO.setPrice(234444);
        refuelingDTO.setKilometer(231111);
        refuelingDTO.setFuelQuantity(25.4);

    }


    @Test
    void getAllRefuelByCarId() throws Exception {

        //GIVEN
        //WHEN
        when(refuelingRepository.findRefuelingByCarId(1L)).thenReturn(List.of(refueling));
        when(refuelingService.getRefuelingByCarId(1L)).thenReturn(List.of(refueling));
        when(refuelingController.getAllRefuelByCarId(1L)).thenReturn(List.of(refueling));

        //THEN
        MvcResult result = mockMvc.perform(get("/v1/car/refuel/{carId}", 1L)
                                                   .contentType(MediaType.APPLICATION_JSON)).andReturn();

        assertEquals(200, result.getResponse().getStatus());

    }

    @Test
    void createNewRefueling() {

        //GIVEN
        given(modelMapper.map(refueling, RefuelingDTO.class)).willReturn(refuelingDTO);
        given(modelMapper.map(refuelingDTO, Refueling.class)).willReturn(refueling);
        given(refuelingRepository.findAll()).willReturn(List.of(refueling));
        given(refuelingService.createNewRefueling(refueling)).willReturn(refueling);


        //WHEN
        ResponseEntity<String> actual = refuelingController.createNewRefueling(refuelingDTO);

        //THEN
        assertEquals(HttpStatus.CREATED, actual.getStatusCode());

    }

    @Test
    void updateRefueling() {

        //GIVEN
        given(modelMapper.map(refueling, RefuelingDTO.class)).willReturn(refuelingDTO);
        given(modelMapper.map(refuelingDTO, Refueling.class)).willReturn(refueling);
        given(refuelingRepository.findAll()).willReturn(List.of(refueling));
        given(refuelingService.updateRefueling(refueling)).willReturn(refueling);


        //WHEN
        ResponseEntity<String> actual = refuelingController.updateRefueling(1L, refuelingDTO);

        //THEN
        assertEquals(HttpStatus.CREATED, actual.getStatusCode());

    }

    @Test
    void deleteRefuelById() {

        //GIVEN
        given(refuelingRepository.findById(1L)).willReturn(Optional.of(refueling));

        //WHEN
        ResponseEntity<String> actual = refuelingController.deleteRefuelById(1L);

        //THEN
        assertEquals(HttpStatus.OK, actual.getStatusCode());

    }
}