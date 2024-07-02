package com.car.carservicebook.controller;

import com.car.carservicebook.dto.OrderedPartDTO;
import com.car.carservicebook.jpa.OrderedPart;
import com.car.carservicebook.service.OrderedPartService;
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
@Tag(name = "Car Parts Name API", description = "The Car Parts Name API")
public class OrderedPartController {


    private final ModelMapper modelMapper;

    private final OrderedPartService orderedPartService;


    @GetMapping("/order/part/car/{id}")
    @ResponseBody
    @SecurityRequirement(name = "bearerToken")
    @Operation(summary = "Get a specific car's all ordered parts by car_id", description = "Return a specific car's all ordered parts")
    public List<OrderedPart> getAllOrderedPartsByCarId(@PathVariable("id") Long car_id) {
        return orderedPartService.getAllOrderedPartsByCarId(car_id);
    }


    @PostMapping("/order/part/new")
    @ResponseBody
    @SecurityRequirement(name = "bearerToken")
    @Operation(summary = "Create new ordered part", description = "Create new ordered part for the appropriate car")
    public ResponseEntity<String> createNewOrderedPart(@RequestBody OrderedPartDTO orderedPartDTO) {
        OrderedPart orderedPartRequest = modelMapper.map(orderedPartDTO, OrderedPart.class);

        OrderedPart newOrderedPart = orderedPartService.createNewOrderedPart(orderedPartRequest);

        OrderedPartDTO orderedPartResponse = modelMapper.map(newOrderedPart, OrderedPartDTO.class);

        return ResponseEntity.status(HttpStatus.CREATED).body("New car part created!" + orderedPartResponse.toString());
    }

    @PostMapping("/order/part/update")
    @ResponseBody
    @SecurityRequirement(name = "bearerToken")
    @Operation(summary = "Update a car part data", description = "Update a car part data in the database")
    public ResponseEntity<String> updateNewOrderedPart(@RequestBody OrderedPartDTO orderedPartDTO) {
        OrderedPart orderedPartRequest = modelMapper.map(orderedPartDTO, OrderedPart.class);

        OrderedPart newOrderedPart = orderedPartService.updateNewOrderedPart(orderedPartRequest);

        OrderedPartDTO orderedPartResponse = modelMapper.map(newOrderedPart, OrderedPartDTO.class);

        return ResponseEntity.status(HttpStatus.CREATED).body("Car part updated!");
    }


    @DeleteMapping("/order/part/delete/{id}")
    @ResponseBody
    @SecurityRequirement(name = "bearerToken")
    @Operation(summary = "Delete a car part by id", description = "Delete a car part by id from the database")
    public ResponseEntity<String> deleteOrderedPartById(@PathVariable("id") Long id) {
        orderedPartService.deleteOrderedPartById(id);

        return ResponseEntity.status(HttpStatus.OK).body("Car part deleted!");
    }


}
