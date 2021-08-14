package com.kodilla.car_rent_backend.controller;


import com.google.gson.Gson;
import com.kodilla.car_rent_backend.domain.User;
import com.kodilla.car_rent_backend.dto.UserDto;
import com.kodilla.car_rent_backend.mapper.UserMapper;
import com.kodilla.car_rent_backend.repository.UserRepository;
import com.kodilla.car_rent_backend.service.UserService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

//import org.junit.Test;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @MockBean
    UserRepository userRepository;

    @MockBean
    UserMapper userMapper;

    @MockBean
    UserService userService;

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void shouldFetchEmptyUserList() throws Exception{
//        Given
        List<UserDto> userDtoList = new ArrayList<>();

        when(userMapper.mapToUserListDto(userService.getAllUsers())).thenReturn(userDtoList);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/user/all")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)))
                        .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    public void shouldGetUser() throws Exception{
//        Given
        User user = new User(
                1L, "FirstName", "Surname", 873847283,
                "email@email.com", 938294819,new ArrayList<>(), new ArrayList<>()
        );

        UserDto userDto = new UserDto(
                1L, "FirstName", "Surname", 873847283,
                "email@email.com", 938294819,new ArrayList<>(), new ArrayList<>()
        );



        when(userService.getUser(1L)).thenReturn(Optional.of(user));
        when(userMapper.mapToUserDto(any())).thenReturn(userDto);

//        When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                .get("/v1/user/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", Matchers.is("FirstName")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.surName", Matchers.is("Surname")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phone", Matchers.is(873847283)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", Matchers.is("email@email.com")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.pesel", Matchers.is(938294819)))
                .andExpect(MockMvcResultMatchers.status().is(200));

    }

    @Test
    public void shouldGetAllUser() throws Exception {
    List<User> userList = new ArrayList<>();
    when(userService.getAllUsers()).thenReturn(userList);



        //        When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                .get("/v1/user/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)))
                .andExpect(MockMvcResultMatchers.status().is(200));


    }

    @Test
    public void shouldDeleteUser() throws Exception{
//        Given
        User user = new User(
                1L, "FirstName", "Surname", 873847283,
                "email@email.com", 938294819,new ArrayList<>(), new ArrayList<>()
        );

        when(userService.saveUser(user)).thenReturn(user);

//        When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                .delete("/v1/user/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    public void shouldCreateUser() throws Exception{
//        Given
        User user = new User(
                1L, "FirstName", "Surname", 873847283,
                "email@email.com", 938294819,new ArrayList<>(), new ArrayList<>()
        );

        UserDto userDto = new UserDto(
                1L, "FirstName", "Surname", 873847283,
                "email@email.com", 938294819,new ArrayList<>(), new ArrayList<>()
        );

        when(userService.saveUser(any(User.class))).thenReturn(new User(1L, "FirstName", "Surname", 873847283,
                "email@email.com", 938294819,new ArrayList<>(), new ArrayList<>()));

        Gson gson = new Gson();
        String jsonContent = gson.toJson(userDto);

//        When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    public void shouldUpdateUser() throws Exception {
//        Given
        User user = new User(
                1L, "FirstName", "Surname", 873847283,
                "email@email.com", 938294819,new ArrayList<>(), new ArrayList<>()
        );

        UserDto userDto = new UserDto(
                1L, "FirstName", "Surname", 873847283,
                "email@email.com", 938294819,new ArrayList<>(), new ArrayList<>()
        );
        UserDto userDto2 = new UserDto(
                4L, "UpdateName", "Surname", 873847283,
                "email@email.com", 938294819,new ArrayList<>(), new ArrayList<>()
        );

        when(userService.saveUser(user)).thenReturn(new User(4L, "UpdateName", "Surname", 873847283,
                "email@email.com", 938294819,new ArrayList<>(), new ArrayList<>()));
        when(userMapper.mapToUserDto(any())).thenReturn(userDto2);
        when(userMapper.mapToUser(any())).thenReturn(user);

        Gson gson = new Gson();
        String jasonContent = gson.toJson(userDto);

//        When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                .put("/v1/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jasonContent))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(4)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", Matchers.is("UpdateName")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.surName", Matchers.is("Surname")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phone", Matchers.is(873847283)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", Matchers.is("email@email.com")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.pesel", Matchers.is(938294819)))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}
