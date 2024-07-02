package com.car.carservicebook.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OilConsumptionRepository extends JpaRepository<OilConsumption, Long> {

    List<OilConsumption> findOilConsumptionByCarId(Long car_id);

    Optional<OilConsumption> findOilConsumptionById(Long id);

}
