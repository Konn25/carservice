package com.car.carservicebook.controller;

import com.car.carservicebook.dto.RepairNameDTO;
import com.car.carservicebook.jpa.Repair;
import com.car.carservicebook.jpa.RepairName;
import com.car.carservicebook.service.RepairNameService;
import com.car.carservicebook.service.RepairService;
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
@Tag(name = "Repair Name API", description = "The Repair Name API")
public class RepairNameController {

    private final ModelMapper modelMapper;

    private final RepairNameService repairNameService;

    @GetMapping("/repair/name")
    @ResponseBody
    @SecurityRequirement(name = "bearerToken")
    @Operation(summary = "Get all repair type", description = "Return all repair type")
    public List<RepairName> getAllRepairName(){
        return repairNameService.getAllRepairName();
    }

    @PostMapping("/repair/name/new")
    @ResponseBody
    @SecurityRequirement(name = "bearerToken")
    @Operation(summary = "Create new repair type", description = "Create new repair type")
    public ResponseEntity<String> createNewRepairName(@RequestBody RepairNameDTO repairNameDTO){

        RepairName repairNameRequest = modelMapper.map(repairNameDTO, RepairName.class);

        RepairName newRepairName = repairNameService.createNewRepairName(repairNameRequest);

        RepairNameDTO repairNameResponse = modelMapper.map(newRepairName, RepairNameDTO.class);

        return ResponseEntity.status(HttpStatus.CREATED).body("New repair type created!");

    }

}
