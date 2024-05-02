package com.car.carservicebook.controller;

import com.car.carservicebook.jpa.Admin;
import com.car.carservicebook.jpa.User;
import com.car.carservicebook.service.AdminService;
import com.car.carservicebook.service.UserService;
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
@Tag(name = "Admin API", description = "The Admin API")
public class AdminController {

    private final ModelMapper modelMapper;

    private final AdminService adminService;

    private final UserService userService;


    @GetMapping("/admin/users")
    @ResponseBody
    @SecurityRequirement(name = "bearerToken")
    @Operation(summary = "Get all user", description = "Return all user from the database")
    public List<User> getAllUser(){
        return userService.getAllUser();
    }

    @GetMapping("/admins")
    @ResponseBody
    @SecurityRequirement(name = "bearerToken")
    @Operation(summary = "Get all admin", description = "Return all admin from the database")
    public List<Admin> getAllAdmin(){
        return adminService.getAllAdmin();
    }

    @DeleteMapping("/admin/delete/{id}")
    @ResponseBody
    @SecurityRequirement(name = "bearerToken")
    @Operation(summary = "Delete an admin by id", description = "Delete an admin by id from the database")
    public ResponseEntity<String> deleteAdminById(@PathVariable("id") Long id) {
        adminService.deleteAdmin(id);
        return ResponseEntity.status(HttpStatus.OK).body("Admin deleted!");
    }

    @DeleteMapping("/admin/delete/user/{id}")
    @ResponseBody
    @SecurityRequirement(name = "bearerToken")
    @Operation(summary = "Delete an admin by id", description = "Delete an admin by id from the database")
    public ResponseEntity<String> deleteUserById(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body("User deleted!");
    }


}
