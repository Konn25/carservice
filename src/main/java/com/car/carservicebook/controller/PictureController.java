package com.car.carservicebook.controller;

import com.car.carservicebook.dto.PictureDTO;
import com.car.carservicebook.jpa.Picture;
import com.car.carservicebook.service.PictureService;
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
@Tag(name = "Picture API", description = "The Picture API")
public class PictureController {

    private final PictureService pictureService;

    private final ModelMapper modelMapper;

    @GetMapping("/car/picture/{id}")
    @SecurityRequirement(name = "bearerToken")
    @Operation(summary = "Get user's picture", description = "Return user's picture by id")
    public List<Picture> getCarPicture(@PathVariable("id") Long id) {
        return pictureService.getPictureById(id);
    }

    @DeleteMapping("/car/picture/delete/{id}")
    @ResponseBody
    @SecurityRequirement(name = "bearerToken")
    @Operation(summary = "Delete a car picture by id", description = "Delete a car picture by id from the database")
    public ResponseEntity<String> deletePictureById(@PathVariable("id") Long id) {
        pictureService.deletePictureById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Car picture deleted!");
    }

    @PostMapping("/car/picture/new")
    @ResponseBody
    @SecurityRequirement(name = "bearerToken")
    @Operation(summary = "Create new picture", description = "Create new picture for the appropriate car")
    public ResponseEntity<String> createNewPicture(@RequestBody PictureDTO pictureDTO) {

        Picture pictureRequest = modelMapper.map(pictureDTO, Picture.class);

        Picture newPicture = pictureService.createNewPicture(pictureRequest);

        PictureDTO pictureResponse = modelMapper.map(newPicture, PictureDTO.class);

        return ResponseEntity.status(HttpStatus.CREATED).body("New picture created!" + pictureResponse.toString());
    }


}
