package com.car.carservicebook.controller;

import com.car.carservicebook.dto.UserDTO;
import com.car.carservicebook.service.UserService;
import com.car.carservicebook.jpa.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "http://localhost:4200",allowCredentials="true" )
@RequiredArgsConstructor
@Tag(name = "User API", description = "The User API")
public class UserController {

    private final UserService userService;

    private final ModelMapper modelMapper;

    @GetMapping("/user/data/{email}")
    @ResponseBody
    @SecurityRequirement(name = "bearerToken")
    @Operation(summary = "Get user data by email", description  = "Return user data")
    public Optional<User> getUserDataByEmail(@PathVariable(name = "email") String email){
        return userService.getDataByEmail(email);
    }

    @GetMapping("/users")
    @ResponseBody
    @SecurityRequirement(name = "bearerToken")
    @Operation(summary = "Get user data by email", description  = "Return user data")
    public List<User> getAllUser(){
        return  userService.getAllUser();
    }

    @DeleteMapping("/user/delete/{id}")
    @ResponseBody
    @Transactional
    @SecurityRequirement(name = "bearerToken")
    @Operation(summary = "Delete an user by id", description = "Delete an user by id from the database")
    public ResponseEntity<String> deletePictureById(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body("User deleted!");
    }

    @PostMapping("/user/new")
    @ResponseBody
    @Operation(summary = "Create new user", description  = "Create new user")
    public ResponseEntity<String> createNewUser(@RequestBody UserDTO userDTO) throws NoSuchAlgorithmException {

        User userRequest = modelMapper.map(userDTO, User.class);

        MessageDigest md = MessageDigest.getInstance("SHA-256");

        md.update(userRequest.getPassword().getBytes(StandardCharsets.UTF_8));

        byte[] digest = md.digest();

        userRequest.setPassword(String.format("%064x", new BigInteger(1,digest)));

        User newUser = userService.createNewUser(userRequest);

        UserDTO userResponse = modelMapper.map(newUser, UserDTO.class);

        return ResponseEntity.status(HttpStatus.CREATED).body("User created");
    }


    @PostMapping("/user/update")
    @ResponseBody
    @SecurityRequirement(name = "bearerToken")
    @Operation(summary = "Update user data", description  = "Update user data")
    public User updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

}
