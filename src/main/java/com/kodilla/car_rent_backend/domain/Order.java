package com.kodilla.car_rent_backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ORDER_RENT")
public class Order {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    private Long id;

    @NotNull
    @Column(name = "DATE_OF_RENTAL")
    private LocalDate dataOfRental;

    @NotNull
    @Column(name = "DATE_OF_RETURN")
    private LocalDate dateOfReturn;

    @NotNull
    @Column(name = "RENT_COST")
    private double rentCost;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "VEHICLE_ID")
    private Vehicle vehicle;

    @OneToOne
    @JoinColumn(name = "INVOICE_ID")
    private Invoice invoice;


}