package com.car.carservicebook.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    List<Admin> findAll();

    Optional<Admin> findAdminById(Long id);


}
