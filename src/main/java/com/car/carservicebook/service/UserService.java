package com.car.carservicebook.service;

import com.car.carservicebook.jpa.Car;
import com.car.carservicebook.jpa.CarRepository;
import com.car.carservicebook.jpa.User;
import com.car.carservicebook.jpa.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserServiceInterface {

    private final UserRepository userRepository;

    private final CarRepository carRepository;

    @Override
    public Optional<User> getDataByEmail(@RequestBody String email) {
            return userRepository.findUserByEmail(email);
    }

    @Override
    public User createNewUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findUserById(id);
    }

    @Override
    public void deleteUserById(Long id) {
        Optional<User> findUser = Optional.ofNullable(userRepository.findUserById(id));

        if (findUser.isPresent() && findUser.get().getId() > 0) {
            List<Car> findCars = carRepository.findCarByUser_Id(findUser.get().getId());

            for (Car car : findCars) {
                carRepository.deleteById(car.getId());
            }

            userRepository.deleteById(id);
        }
    }
}
