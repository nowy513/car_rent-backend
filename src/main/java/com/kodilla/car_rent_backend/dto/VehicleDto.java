package com.kodilla.car_rent_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class VehicleDto {
    private Long id;
    private String brand;
    private String model;
    private String registration;
    private Boolean availability;
    private double pricePerDay;
    private VehicleParamDto vehicleParamId;
    private List<OrderDto> orderDtoList;
}