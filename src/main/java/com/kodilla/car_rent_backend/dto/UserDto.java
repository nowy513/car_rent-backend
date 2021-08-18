package com.kodilla.car_rent_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserDto {
    private Long id;
    private String firstName;
    private String surName;
    private int phone;
    private String email;
    private Long pesel;
    private List<Long> orderDtoList;
    private List<Long> invoiceId;


}