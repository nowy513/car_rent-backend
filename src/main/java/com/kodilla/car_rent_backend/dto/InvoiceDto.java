package com.kodilla.car_rent_backend.dto;

import lombok.*;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
public class InvoiceDto {
    private Long id;
    private String invoiceNumber;
    private UserDto userId;
    private OrderDto orderDto;
}