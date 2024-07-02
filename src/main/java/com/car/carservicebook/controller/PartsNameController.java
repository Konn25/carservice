package com.car.carservicebook.controller;

import com.car.carservicebook.jpa.PartsName;
import com.car.carservicebook.service.PartsNameService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RequiredArgsConstructor
@Tag(name = "Parts Name API", description = "The Parts Name API")
public class PartsNameController {

    private final PartsNameService partsNameService;

    @GetMapping("/parts/name")
    @ResponseBody
    @SecurityRequirement(name = "bearerToken")
    @Operation(summary = "Get all car part type", description = "Return all car part type")
    public List<PartsName> getAllPartsName(){
        return partsNameService.getAllName();
    }



}
