package com.car.carservicebook.controller;

import com.car.carservicebook.jpa.Picture;
import com.car.carservicebook.service.PictureService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RequiredArgsConstructor
@Tag(name = "Picture API", description = "The Picture API")
public class PictureController {

    private final PictureService pictureService;

    @GetMapping("/car/picture/{id}")
    @Transactional
    @SecurityRequirement(name = "bearerToken")
    @Operation(summary = "Get user's picture", description = "Return user's picture by id")
    public ResponseEntity<?> getCarPicture(@PathVariable("id") Long id) {

        Optional<Picture> pictureList = pictureService.getPictureById(id);

        byte[] image = pictureService.getImage(pictureList.get().getId(), pictureList.get().getName());

        return ResponseEntity.status(HttpStatus.OK)
                                           .contentType(MediaType.valueOf("image/png"))
                                           .body(image);
    }

    @GetMapping("/car/pictures/{id}")
    @SecurityRequirement(name = "bearerToken")
    @Transactional
    @Operation(summary = "Get user's picture", description = "Return user's picture by id")
    public List<Picture> getCarAllPicture(@PathVariable("id") Long carId) {

        return pictureService.getCarAllPicture(carId);
    }

    @DeleteMapping("/car/picture/delete/{id}")
    @ResponseBody
    @Transactional
    @SecurityRequirement(name = "bearerToken")
    @Operation(summary = "Delete a car picture by id", description = "Delete a car picture by id from the database")
    public ResponseEntity<String> deletePictureById(@PathVariable("id") Long id) {
        pictureService.deletePictureById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Car picture deleted!");
    }

    @PostMapping("/car/picture/new/{carId}")
    @ResponseBody
    @SecurityRequirement(name = "bearerToken")
    @Operation(summary = "Create new picture", description = "Create new picture for the appropriate car")
    public ResponseEntity<?> createNewPicture(@RequestParam("image") MultipartFile file, @PathVariable("carId") Long carId) throws IOException {

        pictureService.createNewPicture(file, carId);

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

}
