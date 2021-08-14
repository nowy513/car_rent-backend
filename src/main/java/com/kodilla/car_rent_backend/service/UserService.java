package com.kodilla.car_rent_backend.service;

import com.kodilla.car_rent_backend.domain.User;
import com.kodilla.car_rent_backend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User saveUser(final User user){
        return userRepository.save(user);
    }

    public Optional<User> getUser(final Long id){
        return userRepository.findById(id);
    }

    public void deleteUser(final Long id){
        userRepository.deleteById(id);
    }
}