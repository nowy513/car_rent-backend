package com.kodilla.car_rent_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InvoiceDto {
    private Long id;
    private String invoiceNumber;
    private Long userId;
    private Long orderDto;
}