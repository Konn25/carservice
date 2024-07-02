package com.car.carservicebook.controller;

import com.car.carservicebook.dto.OilConsumptionDTO;
import com.car.carservicebook.jpa.OilConsumption;
import com.car.carservicebook.service.OilConsumptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RequiredArgsConstructor
@Tag(name = "Oil Consumption API", description = "The Oil Consumption API")
public class OilConsumptionController {

    private final ModelMapper modelMapper;

    private final OilConsumptionService oilConsumptionService;


    @GetMapping("/oil/consumption/{carId}")
    @ResponseBody
    @SecurityRequirement(name = "bearerToken")
    @Operation(summary = "Get a specific car all oil consumption", description = "Return a specific car's all oil consumption by car_id")
    public List<OilConsumption> getAllOilConsumptionByCarId(@PathVariable("carId") Long car_id){
        return oilConsumptionService.getAllOilConsumptionByCarId(car_id);
    }

    @PostMapping("/oil/consumption/new")
    @ResponseBody
    @SecurityRequirement(name = "bearerToken")
    @Operation(summary = "Create new oil consumption", description = "Create new oil consumption for the appropriate car")
    public ResponseEntity<String> createNewOilConsumption(@RequestBody OilConsumptionDTO oilConsumptionDTO){
        OilConsumption oilConsumptionRequest = modelMapper.map(oilConsumptionDTO, OilConsumption.class);

        OilConsumption newOilConsumption = oilConsumptionService.createNewOilConsumption(oilConsumptionRequest);

        OilConsumptionDTO oilConsumptionResponse = modelMapper.map(newOilConsumption, OilConsumptionDTO.class);

        return ResponseEntity.status(HttpStatus.CREATED).body("New oil consumption created!");
    }

    @PostMapping("/oil/consumption/update")
    @ResponseBody
    @SecurityRequirement(name = "bearerToken")
    @Operation(summary = "Update an oil consumption", description = "Update an oil consumption in the database")
    public ResponseEntity<String> updateOilConsumption(@RequestBody OilConsumptionDTO oilConsumptionDTO){
        OilConsumption oilConsumptionRequest = modelMapper.map(oilConsumptionDTO, OilConsumption.class);

        OilConsumption newOilConsumption = oilConsumptionService.createNewOilConsumption(oilConsumptionRequest);

        OilConsumptionDTO oilConsumptionResponse = modelMapper.map(newOilConsumption, OilConsumptionDTO.class);

        return ResponseEntity.status(HttpStatus.OK).body("Oil consumption Updated!");
    }


    @DeleteMapping("/oil/consumption/delete/{id}")
    @ResponseBody
    @SecurityRequirement(name = "bearerToken")
    @Operation(summary = "Delete an oil consumption by id", description = "Delete an oil consumption from the database")
    public ResponseEntity<String> deleteOilConsumptionById(@PathVariable("id") Long id){
        oilConsumptionService.deleteOilConsumptionById(id);

        return ResponseEntity.status(HttpStatus.OK).body("Oil consumption deleted!");
    }



}
