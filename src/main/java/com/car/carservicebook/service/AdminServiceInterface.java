package com.car.carservicebook.service;

import com.car.carservicebook.jpa.Admin;
import com.car.carservicebook.jpa.User;

import java.util.List;

public interface AdminServiceInterface {

    List<Admin> getAllAdmin();

    List<User> getAllUser();

    void deleteAdmin(Long id);

    void deleteUser(Long userId);

}
