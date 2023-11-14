package com.car.carservicebook.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<com.car.carservicebook.jpa.User> findUserByEmail(String email);

    User findUserById(Long id);

}
