package com.car.carservicebook.service;

import com.car.carservicebook.jpa.Refueling;

import java.util.List;

public interface RefuelingServiceInterface {

    List<Refueling> getRefuelingByCarId(Long carId);

    Refueling createNewRefueling(Refueling refueling);

    Refueling updateRefueling(Refueling refueling);

    void deleteRefueling(Long id);


}
