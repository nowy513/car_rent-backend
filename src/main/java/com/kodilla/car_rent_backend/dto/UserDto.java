package com.kodilla.car_rent_backend.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

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
    private int pesel;
    private List<OrderDto> orderDtoList;
    private List<InvoiceDto> invoiceId;

}