package com.car.carservicebook.service;

import com.car.carservicebook.jpa.Car;
import com.car.carservicebook.jpa.CarRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarService implements CarServiceInterface {

    @Autowired
    private final CarRepository carRepository;

    @Override
    public List<Car> getUserAllCar(Long userId) {
        return carRepository.findCarByUser_Id(userId);
    }

    @Override
    public Optional<Car> getCarById(Long id) {
        return carRepository.findCarById(id);
    }

    @Override
    public Car createNewCar(Car car) {
        return carRepository.save(car);
    }

    @Override
    public Car updateCar(Car car) {
        Optional<Car> foundCar = carRepository.findCarById(car.getId());

        foundCar.ifPresent(value -> car.setId(value.getId()));
        return carRepository.save(car);
    }

    @Override
    @Transactional
    public void deleteCar(Long id) {
        Optional<Car> foundCar = carRepository.findCarById(id);

        if (foundCar.isPresent() && foundCar.get().getId() > 0) {
            carRepository.deleteById(id);
        }

    }

}
