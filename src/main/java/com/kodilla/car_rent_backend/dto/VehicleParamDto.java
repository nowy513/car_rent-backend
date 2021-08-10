package com.kodilla.car_rent_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class VehicleParamDto {
    private Long id;
    private String brand;
    private String model;
    private String color;
    private String bodyType;
    private String fuelType;
    private int numberOfSeats;
    private int numberOfDoors;
    private double engineCapacity;
    private BigDecimal vehicleMileage;
    private int vinNumber;
    private double power;
    private VehicleDto vehicleId;
}