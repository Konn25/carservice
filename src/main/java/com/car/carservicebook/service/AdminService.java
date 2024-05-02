package com.car.carservicebook.service;

import com.car.carservicebook.jpa.Admin;
import com.car.carservicebook.jpa.AdminRepository;
import com.car.carservicebook.jpa.User;
import com.car.carservicebook.jpa.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService implements AdminServiceInterface{

    @Autowired
    private final AdminRepository adminRepository;

    private final UserRepository userRepository;


    @Override
    public List<Admin> getAllAdmin() {
        return adminRepository.findAll();
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public void deleteAdmin(Long id) {
        Optional<Admin> foundAdmin = adminRepository.findAdminById(id);

        if (foundAdmin.isPresent() && foundAdmin.get().getId() > 0) {
            adminRepository.deleteById(id);
        }
    }

    @Override
    public void deleteUser(Long userId) {
        Optional<User> findUser = userRepository.findById(userId);

        if (findUser.isPresent() && findUser.get().getId() > 0) {
            userRepository.deleteById(userId);
        }
    }
}
