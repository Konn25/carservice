package com.car.carservicebook.controller;

import com.car.carservicebook.dto.UserDTO;
import com.car.carservicebook.service.UserService;
import com.car.carservicebook.jpa.User;
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

    @PostMapping("/user/new")
    @ResponseBody
    @Operation(summary = "Create new user", description  = "Create new user")
    public ResponseEntity<String> createNewUser(@RequestBody UserDTO userDTO){

        User userRequest = modelMapper.map(userDTO, User.class);

        User newUser = userService.createNewUser(userRequest);

        UserDTO userResponse = modelMapper.map(newUser, UserDTO.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(String.valueOf(userResponse));
    }


    @PostMapping("/user/update")
    @ResponseBody
    @SecurityRequirement(name = "bearerToken")
    @Operation(summary = "Update user data", description  = "Update user data")
    public User updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

}
