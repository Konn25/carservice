package com.car.carservicebook.controller;


import com.car.carservicebook.config.JwtUtils;
import com.car.carservicebook.dao.UserDAO;
import com.car.carservicebook.dto.AuthenticationDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@Tag(name = "Authentication Api")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final UserDAO userDAO;
    private final JwtUtils jwtUtils;

    @PostMapping("/authenticate")
    @Operation(summary = "Generate an authentication token", description  = "Return an authentication token (user or admin)")
    public ResponseEntity<String> authUser(@RequestBody AuthenticationDTO request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword())
        );

        final UserDetails userDetails = userDAO.findUserByEmail(request.getEmail());
        if(userDetails != null){
            return ResponseEntity.ok().body(jwtUtils.generateToken(userDetails));
        }

        return ResponseEntity.status(HttpStatusCode.valueOf(403)).body("Some error has occurred");
    }


}
