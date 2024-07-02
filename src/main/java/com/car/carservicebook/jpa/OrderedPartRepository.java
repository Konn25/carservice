package com.car.carservicebook.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderedPartRepository extends JpaRepository<OrderedPart, Long> {

    List<OrderedPart> findOrderedPartByCarId(Long car_id);

    Optional<OrderedPart> findOrderedPartById(Long id);



}
