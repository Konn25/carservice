package com.car.carservicebook.service;

import com.car.carservicebook.jpa.Car;

import java.util.List;
import java.util.Optional;

public interface CarServiceInterface {

    List<Car> getUserAllCar(Long userId);

    Optional<Car> getCarById(Long id);

    Car createNewCar(Car car);

    Car updateCar(Car car);

    void deleteCar(Long id);


}
