package com.car.carservicebook.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RefuelingRepository extends JpaRepository<Refueling, Long> {

    List<Refueling> findRefuelingByCarId(Long carId);

}
