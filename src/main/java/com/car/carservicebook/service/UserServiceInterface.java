package com.car.carservicebook.service;

import com.car.carservicebook.jpa.User;

import java.util.List;
import java.util.Optional;

public interface UserServiceInterface {

    Optional<User> getDataByEmail(String email);

    User createNewUser(User user);

    User updateUser(User user);

    List<User> getAllUser();

    User findUserById(Long id);

    void deleteUserById(Long id);

}
