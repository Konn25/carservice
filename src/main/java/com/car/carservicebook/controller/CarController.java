package com.car.carservicebook.controller;

import com.car.carservicebook.dto.CarDTO;
import com.car.carservicebook.jpa.Car;
import com.car.carservicebook.jpa.User;
import com.car.carservicebook.service.CarService;
import com.car.carservicebook.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RequiredArgsConstructor
@Tag(name = "Car API", description = "The Car API")
public class CarController {

    private final ModelMapper modelMapper;

    private final CarService carService;

    private final UserService userService;


    @GetMapping("/car/all/{userId}")
    @ResponseBody
    @SecurityRequirement(name = "bearerToken")
    @Operation(summary = "Get user's all car by id", description = "Return user's all car by user id")
    public List<Car> getUserAllCarById(@PathVariable("userId") Long userId) {
        return carService.getUserAllCar(userId);
    }

    @GetMapping("/car/{id}")
    @ResponseBody
    @SecurityRequirement(name = "bearerToken")
    @Operation(summary = "Get user's car by id", description = "Return user's car by car id")
    public Optional<Car> getCarById(@PathVariable("id") Long id) {

        return carService.getCarById(id);
    }

    @PostMapping("/car/new")
    @ResponseBody
    @SecurityRequirement(name = "bearerToken")
    @Operation(summary = "Create new car", description = "Create new car for the appropriate user")
    public ResponseEntity<String> createNewCar(@RequestBody CarDTO carDTO) {

        Car carRequest = modelMapper.map(carDTO, Car.class);

        User getuser =  userService.findUserById(carDTO.getUser_id());

        carRequest.setUser(getuser);

        Car newCar = carService.createNewCar(carRequest);

        CarDTO carResponse = modelMapper.map(newCar, CarDTO.class);

        return ResponseEntity.status(HttpStatus.CREATED).body("New car created!" + carResponse.toString());
    }

    @PostMapping("/car/update")
    @ResponseBody
    @SecurityRequirement(name = "bearerToken")
    @Operation(summary = "Update a car data", description = "Update a car data in the database")
    public ResponseEntity<String> updateCar(@RequestBody CarDTO carDTO) {

        Car carRequest = modelMapper.map(carDTO, Car.class);

        carRequest.setId(carDTO.getId());

        System.out.println("USERID: "+ carDTO.getUser_id());

        User newUser = new User();

        newUser.setId(carDTO.getUser_id());

        carRequest.setUser(newUser);

        Car newCar = carService.updateCar(carRequest);

        CarDTO carResponse = modelMapper.map(newCar, CarDTO.class);

        return ResponseEntity.status(HttpStatus.CREATED).body("Car updated! Id:" + carResponse.getId());
    }


    @DeleteMapping("/car/delete/{id}")
    @ResponseBody
    @SecurityRequirement(name = "bearerToken")
    @Operation(summary = "Delete a car by id", description = "Delete a car by id from the database")
    public ResponseEntity<String> deleteCarById(@PathVariable("id") Long id) {
        carService.deleteCar(id);
        return ResponseEntity.status(HttpStatus.OK).body("Car data deleted!");
    }




}
