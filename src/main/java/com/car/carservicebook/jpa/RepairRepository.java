package com.car.carservicebook.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RepairRepository extends JpaRepository<Repair, Long> {

    List<Repair> findRepairByCarId(Long carId);

    Repair findRepairById(Long id);

    Optional<Repair> findById(Long id);

}
