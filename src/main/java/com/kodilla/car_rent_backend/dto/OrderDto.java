package com.kodilla.car_rent_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderDto {
    private Long id;
    private LocalDate dataOfRental;
    private LocalDate dateOfReturn;
    private double rentCost;
    private Long userId;
    private Long vehicleDto;
    private Long invoiceDto;

}