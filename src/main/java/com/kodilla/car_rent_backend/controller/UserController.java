package com.kodilla.car_rent_backend.controller;

import com.kodilla.car_rent_backend.dto.UserDto;
import com.kodilla.car_rent_backend.exception.UserNotFoundException;
import com.kodilla.car_rent_backend.mapper.UserMapper;
import com.kodilla.car_rent_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public List<UserDto> getUsers(){
        return userMapper.mapToUserListDto(userService.getAllUsers());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{userId}")
    public UserDto getUser (@PathVariable Long userId) throws UserNotFoundException {
        return userMapper.mapToUserDto(userService.getUser(userId).orElseThrow(UserNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.POST,consumes = APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody UserDto userDto){
        userService.saveUser(userMapper.mapToUser(userDto));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{userId}")
    public void deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public UserDto updateUser(@RequestBody UserDto userDto) throws UserNotFoundException{
        return userMapper.mapToUserDto(userService.saveUser(userMapper.mapToUser(userDto)));
    }
}