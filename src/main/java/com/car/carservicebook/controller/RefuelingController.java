package com.car.carservicebook.controller;

import com.car.carservicebook.dto.RefuelingDTO;
import com.car.carservicebook.jpa.Car;
import com.car.carservicebook.jpa.Refueling;
import com.car.carservicebook.service.CarService;
import com.car.carservicebook.service.RefuelingService;
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
@Tag(name = "Refueling API", description = "The Refueling API")
public class RefuelingController {

    private final RefuelingService refuelingService;

    private final CarService carService;

    private final ModelMapper modelMapper;

    @GetMapping("/car/refuel/{carId}")
    @ResponseBody
    @SecurityRequirement(name = "bearerToken")
    @Operation(summary = "Get user's all refueling by car id", description = "Return user's all refueling by car id")
    public List<Refueling> getAllRefuelByCarId(@PathVariable("carId") Long carId) {
        return refuelingService.getRefuelingByCarId(carId);
    }

    @PostMapping("/car/refuel/new")
    @ResponseBody
    @SecurityRequirement(name = "bearerToken")
    @Operation(summary = "Create new refueling", description = "Create new refueling")
    public ResponseEntity<String> createNewRefueling(@RequestBody RefuelingDTO refuelingDTO) {

        Refueling refuelingRequest = modelMapper.map(refuelingDTO, Refueling.class);

        Optional<Car> car = carService.getCarById(refuelingDTO.getCar_id());

        car.ifPresent(refuelingRequest::setCar);

        Refueling newRefueling = refuelingService.createNewRefueling(refuelingRequest);

        RefuelingDTO refuelingResponse = modelMapper.map(newRefueling, RefuelingDTO.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(String.valueOf(modelMapper.map(refuelingResponse, Car.class)));
    }

    @PostMapping("/car/refuel/update")
    @ResponseBody
    @SecurityRequirement(name = "bearerToken")
    @Operation(summary = "Update a refueling data", description = "Update refueling data in the database")
    public Refueling updateRefueling(@RequestBody Refueling refueling) {
        return refuelingService.updateRefueling(refueling);
    }

    @DeleteMapping("/car/refuel/delete/{id}")
    @ResponseBody
    @SecurityRequirement(name = "bearerToken")
    @Operation(summary = "Delete refueling data by id", description = "Delete refueling data by id")
    public ResponseEntity<String> deleteRefuelById(@PathVariable("id") Long id) {
        refuelingService.deleteRefueling(id);

        return ResponseEntity.status(HttpStatus.OK).body("Refueling data deleted!");
    }


}
