package com.car.carservicebook.controller;

import com.car.carservicebook.dto.CarDTO;
import com.car.carservicebook.jpa.Car;
import com.car.carservicebook.jpa.CarRepository;
import com.car.carservicebook.jpa.User;
import com.car.carservicebook.jpa.UserRepository;
import com.car.carservicebook.service.CarService;
import com.car.carservicebook.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@MockitoSettings(strictness = Strictness.LENIENT)
class CarControllerTest {

    @Mock
    private ModelMapper modelMapper;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private CarService carService;

    @Mock
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarController carController;

    private final User newUser = new User();

    private final Car newCar = new Car();
    private final Car newCar2 = new Car();

    private final CarDTO carDTO = new CarDTO();


    @BeforeEach
    void setUp() {

        mockMvc = MockMvcBuilders.standaloneSetup(carController).build();

        userService = new UserService(userRepository,carRepository);
        carService = new CarService(carRepository);
        carController = new CarController(modelMapper, carService, userService);


        newUser.setId(1L);
        newUser.setName("JavaTest");
        newUser.setEmail("test@test.com");
        newUser.setPassword("123");
        newUser.setNickName("Elekk");

        newCar.setId(1L);
        newCar.setUser(newUser);
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
        newCar2.setUser(newUser);
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

        newUser.setCars(List.of(newCar, newCar2));

        carDTO.setId(1L);
        carDTO.setFuel("Petrol");
        carDTO.setMotor(1.9);
        carDTO.setKilometer(435213);
        carDTO.setManufacturer("Java");
        carDTO.setPrice(22223333);
        carDTO.setType("Spring");
        carDTO.setYear(2005);

    }

    @Test
    void getUserAllCarById() throws Exception {

        //GIVEN
        carRepository.save(newCar);
        carRepository.save(newCar2);

        userRepository.save(newUser);

        List<Car> carList = List.of(newCar, newCar2);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v1/car/all/{userId}", 1)
                                                              .contentType(MediaType.APPLICATION_JSON)
                                                              .content(asJsonString(carList));

        //WHEN
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        //THEN
        assertEquals(200, result.getResponse().getStatus());


    }

    @Test
    void getCarById() throws Exception {

        //GIVEN
        //WHEN
        when(carService.getCarById(1L)).thenReturn(Optional.of(newCar));

        MvcResult result = mockMvc.perform(get("/v1/car/{id}", 1L)
                                                   .contentType(MediaType.APPLICATION_JSON)).andReturn();

        //THEN
        assertEquals(200, result.getResponse().getStatus());

    }

    @Test
    void createNewCar() {

        //GIVEN
        given(modelMapper.map(newCar2, CarDTO.class)).willReturn(carDTO);
        given(modelMapper.map(carDTO, Car.class)).willReturn(newCar2);
        given(carRepository.findAll()).willReturn(List.of(newCar2));
        given(carService.createNewCar(newCar2)).willReturn(newCar2);

        //WHEN
        ResponseEntity<String> actual = carController.createNewCar(carDTO);

        //THEN
        assertEquals(HttpStatus.CREATED, actual.getStatusCode());
    }

    @Test
    void updateCar() {

        //GIVEN
        given(modelMapper.map(newCar2, CarDTO.class)).willReturn(carDTO);
        given(modelMapper.map(carDTO, Car.class)).willReturn(newCar2);
        given(carRepository.findAll()).willReturn(List.of(newCar2));
        given(carService.updateCar(newCar2)).willReturn(newCar2);

        //WHEN
        ResponseEntity<String> actual = carController.updateCar(carDTO);

        //THEN
        assertEquals(HttpStatus.CREATED, actual.getStatusCode());
    }

    @Test
    void deleteCarById() {

        //GIVEN
        given(carRepository.findCarById(1L)).willReturn(Optional.of(newCar));

        //WHEN
        ResponseEntity<String> actual = carController.deleteCarById(1L);

        //THEN
        assertEquals(HttpStatus.OK, actual.getStatusCode());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}