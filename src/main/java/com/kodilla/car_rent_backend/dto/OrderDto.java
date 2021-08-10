package com.kodilla.car_rent_backend.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Getter
public class OrderDto {
    private Long id;
    private LocalDate dataOfRental;
    private LocalDate dateOfReturn;
    private double rentCost;
    private UserDto userId;
    private VehicleDto vehicleDto;
    private InvoiceDto invoiceDto;
}