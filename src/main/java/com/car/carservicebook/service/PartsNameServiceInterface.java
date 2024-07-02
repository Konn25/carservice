package com.car.carservicebook.service;

import com.car.carservicebook.jpa.PartsName;

import java.util.List;

public interface PartsNameServiceInterface {

    PartsName getPartsNameById(Long id);

    List<PartsName> getAllName();


}
