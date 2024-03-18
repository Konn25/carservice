package com.car.carservicebook.controller;

import com.car.carservicebook.dto.UserDTO;

import com.car.carservicebook.jpa.User;
import com.car.carservicebook.jpa.UserRepository;
import com.car.carservicebook.service.UserService;
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
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
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
class UserControllerTest {

    @Mock
    private ModelMapper modelMapper;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private final User user = new User();

    private final UserDTO userDTO = new UserDTO();

    @BeforeEach
    void setUp() {

        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        userService = new UserService(userRepository);

        userController = new UserController(userService, modelMapper);

        user.setId(1L);
        user.setName("Test");
        user.setEmail("test@test.com");
        user.setNickName("Elek");
        user.setPassword("123");
        user.setCars(new ArrayList<>());

        userDTO.setId(1L);
        userDTO.setName("Test");
        userDTO.setNickName("Elek");
        userDTO.setEmail("test@test.com");
        userDTO.setPassword("123");

    }

    @Test
    void getUserDataByEmail() throws Exception {

        //GIVEN
        //WHEN
        when(userService.getDataByEmail(user.getEmail())).thenReturn(Optional.of(user));

        MvcResult result = mockMvc.perform(get("/v1/user/data/{email}", user.getEmail())).andReturn();

        //THEN
        assertEquals(200, result.getResponse().getStatus());

    }

    @Test
    void createNewUser() throws NoSuchAlgorithmException {

        //GIVEN
        given(modelMapper.map(user, UserDTO.class)).willReturn(userDTO);
        given(modelMapper.map(userDTO, User.class)).willReturn(user);
        given(userRepository.findAll()).willReturn(List.of(user));
        given(userService.createNewUser(user)).willReturn(user);


        //WHEN
        ResponseEntity<String> actual = userController.createNewUser(userDTO);

        //THEN
        assertEquals(HttpStatus.CREATED, actual.getStatusCode());

    }

    @Test
    void updateUser() {

        //GIVEN
        given(modelMapper.map(user, UserDTO.class)).willReturn(userDTO);
        given(modelMapper.map(userDTO, User.class)).willReturn(user);
        given(userRepository.findAll()).willReturn(List.of(user));
        given(userService.updateUser(user)).willReturn(user);


        //WHEN
        User actual = userController.updateUser(user);

        //THEN
        assertEquals(user,actual);

    }
}