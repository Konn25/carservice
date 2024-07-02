package com.car.carservicebook.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PartsNameRepository extends JpaRepository<PartsName, Long> {

    PartsName findPartsNameById(Long id);


}
