package com.kodilla.car_rent_backend.service;

import com.kodilla.car_rent_backend.domain.User;
import com.kodilla.car_rent_backend.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Test
    public void getAllUser(){
//        Given
        User user = new User(
                1L, "FirstName", "Surname", 873847283,
                "email@email.com", 938294819L,new ArrayList<>(), new ArrayList<>()
        );
        List<User> userList = new ArrayList<>();
        userList.add(user);
        when(userRepository.findAll()).thenReturn(userList);

//        When
        List<User> users = userService.getAllUsers();

//        Then
        assertEquals(1, users.size());
    }

    @Test
    public void getUser(){
//        Given
        User user = new User(
                1L, "FirstName", "Surname", 873847283,
                "email@email.com", 938294819L,new ArrayList<>(), new ArrayList<>()
        );
        List<User> users = new ArrayList<>();
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

//        When
        Optional<User> userOptional = userService.getUser(1L);

//        Then
        assertEquals(Optional.of(user), userOptional);
    }

    @Test
    public void saveUser(){
//        Given
        User user = new User(
                1L, "FirstName", "Surname", 873847283,
                "email@email.com", 938294819L,new ArrayList<>(), new ArrayList<>()
        );

        when(userRepository.save(user)).thenReturn(user);

//        When
        User saveTest = userService.saveUser(user);

//        Then
        assertEquals("FirstName", saveTest.getFirstName());
        assertEquals(873847283, saveTest.getPhone());

    }

    @Test
    public void deleteUser(){
//        Given
        User user = new User(
                1L, "FirstName", "Surname", 873847283,
                "email@email.com", 938294819L,new ArrayList<>(), new ArrayList<>()
        );
        Long id = user.getId();

        userService.deleteUser(id);

//        When
        Optional<User> userOptional = userService.getUser(1L);

//        Then
        assertFalse(userOptional.isPresent());
    }
}
