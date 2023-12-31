package com.car.carservicebook.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarRepository  extends JpaRepository<Car, Long> {

    List<Car> findCarByUser_Id(Long userId);

    Optional<Car> findCarById(Long id);

}
