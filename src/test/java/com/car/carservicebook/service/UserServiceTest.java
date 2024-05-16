package com.car.carservicebook.service;

import com.car.carservicebook.jpa.CarRepository;
import com.car.carservicebook.jpa.User;
import com.car.carservicebook.jpa.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private UserService userService;

    private final User newUser = new User();


    @BeforeEach
    void setUp() {

        userService = new UserService(userRepository,carRepository);

        newUser.setId(1L);
        newUser.setName("Test Elek");
        newUser.setEmail("test@test.com");
        newUser.setPassword("1234");
        newUser.setNickName("Elekk");
        newUser.setCars(null);

    }

    @Test
    void getDataByEmail() {

        //GIVEN
        //WHEN
        when(userRepository.findUserByEmail(newUser.getEmail())).thenReturn(Optional.of(newUser));

        //THEN
        Optional<User> result = userService.getDataByEmail(newUser.getEmail());

        assertTrue(result.isPresent(), "The Optional<User> object is empty");
        assertEquals(newUser.getEmail(), result.get().getEmail(),
                "The email address does not match the expected one");

        verify(userRepository).findUserByEmail(newUser.getEmail());

    }

    @Test
    void createNewUser() {

        //GIVEN
        //WHEN
        when(userRepository.save(newUser)).thenReturn(newUser);

        //THEN
        assertEquals(newUser, userService.createNewUser(newUser));

    }

    @Test
    void updateUser() {

        //GIVEN
        //WHEN
        when(userRepository.save(any(User.class))).thenReturn(newUser);

        //THEN
        User updateUser = userService.updateUser(newUser);

        assertNotNull(updateUser, "The result can't be null!");
        assertEquals(newUser.getId(), updateUser.getId(), "The IDs must match!");

        verify(userRepository, times(1)).save(newUser);

    }

    @Test
    void getAllUser() {

        //GIVEN
        //WHEN
        when(userRepository.findAll()).thenReturn(List.of(newUser));

        //THEN
        assertEquals(List.of(newUser), userService.getAllUser());

    }

    @Test
    void findUserById() {

        //GIVEN
        //WHEN
        when(userRepository.findUserById(1L)).thenReturn(newUser);

        //THEN
        assertEquals(newUser, userService.findUserById(1L));

    }
}