package com.car.carservicebook.controller;

import com.car.carservicebook.dto.CarDTO;
import com.car.carservicebook.dto.RepairDTO;
import com.car.carservicebook.jpa.*;
import com.car.carservicebook.service.CarService;
import com.car.carservicebook.service.RepairService;
import com.car.carservicebook.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RequiredArgsConstructor
@Tag(name = "Repair API", description = "The Repair API")
public class RepairController {

    private final RepairService repairService;

    private final CarService carService;

    private final ModelMapper modelMapper;

    private final UserService userService;

    @GetMapping("/car/repair/{carId}")
    @ResponseBody
    @SecurityRequirement(name = "bearerToken")
    @Operation(summary = "Get user's all repair by car id", description = "Return user's all repair by car id")
    public List<Repair> getAllRepairByCarId(@PathVariable("carId") Long carId) {
        return repairService.getAllRepairByCarId(carId);
    }


    @PostMapping("/car/repair/new")
    @ResponseBody
    @SecurityRequirement(name = "bearerToken")
    @Operation(summary = "Create new repair data", description = "Create new repair data in the database")
    public ResponseEntity<String> createNewRepair(@RequestBody RepairDTO repairDTO) {

        Repair repairRequest = modelMapper.map(repairDTO, Repair.class);

        Optional<Car> car = carService.getCarById(repairDTO.getCar_id());

        car.ifPresent(repairRequest::setCar);

        Repair newRepair = repairService.createNewRepair(repairRequest);

        RepairDTO repairResponse = modelMapper.map(newRepair, RepairDTO.class);

        return ResponseEntity.status(HttpStatus.CREATED).body("New repair added to database!");
    }

    @DeleteMapping("/car/repair/delete/{id}")
    @ResponseBody
    @SecurityRequirement(name = "bearerToken")
    @Operation(summary = "Delete repair data", description = "Delete repair data by id")
    public ResponseEntity<String> deleteRepairById(@PathVariable("id") Long id) {
        repairService.deleteRepair(id);

        return ResponseEntity.status(HttpStatus.OK).body("Repair data deleted!");
    }

    @PostMapping("/car/repair/update/{id}")
    @ResponseBody
    @Transactional
    @SecurityRequirement(name = "bearerToken")
    @Operation(summary = "Update repair data", description = "Update repair data in the database")
    public ResponseEntity<String> updateRepair(@PathVariable Long id, @RequestBody RepairDTO repairDTO) {

        Repair repairRequest = modelMapper.map(repairDTO, Repair.class);

        repairRequest.setId(id);

        System.out.println(repairDTO.getId()+" carid: "+ repairDTO.getCar_id()+" "+ repairDTO.getPrice()+ " "+ repairDTO.getDate());

        Optional<Car> foundCar = carService.getCarById(repairDTO.getCar_id());

        Car newCar = new Car();
        newCar.setId(foundCar.get().getId());

        repairRequest.setCar(newCar);

        Repair newRepair = repairService.updateRepair(repairRequest);

        RepairDTO repairResponse = modelMapper.map(newRepair, RepairDTO.class);

        return ResponseEntity.status(HttpStatus.CREATED).body("Car updated! Id:");
    }


}
