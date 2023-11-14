package com.car.carservicebook.dao;

import com.car.carservicebook.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.car.carservicebook.config.Roles.ROLE_USER;

@Repository
@RequiredArgsConstructor
public class UserDAO {

    private final UserService userService;

    public UserDetails findUserByEmail(String email) {

        List<UserDetails> APP_USERS = getAllUsersFromDatabase();

        return APP_USERS.stream().filter(u -> u.getUsername().equals(email)).findFirst()
                .orElseThrow(() ->  new UsernameNotFoundException("No user was found"));
    }

    public List<UserDetails> getAllUsersFromDatabase() {

        List<UserDetails> userList = new ArrayList<>();

        List<com.car.carservicebook.jpa.User> clientList = userService.getAllUser();

        for (com.car.carservicebook.jpa.User client : clientList) {
            userList.add(new User(client.getEmail(), client.getPassword(), Collections.singleton(new SimpleGrantedAuthority(ROLE_USER.name()))));
        }

        return userList;

    }

}