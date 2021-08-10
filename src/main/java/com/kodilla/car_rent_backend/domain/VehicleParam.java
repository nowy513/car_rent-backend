package com.kodilla.car_rent_backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "VEHICLE_PARAMETERS")
public class VehicleParam {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "VEHICLE_PARAM_ID", unique = true)
    private Long id;

    @NotNull
    @Column(name = "BRAND")
    private String brand;

    @NotNull
    @Column(name = "MODEL")
    private String model;

    @NotNull
    @Column(name = "COLOR")
    private String color;

    @NotNull
    @Column(name = "BODY_TYPE")
    private String bodyType;

    @NotNull
    @Column(name = "FUEL_TYPE")
    private String fuelType;

    @NotNull
    @Column(name = "NUMBER_OF_SEATS")
    private int numberOfSeats;

    @NotNull
    @Column(name = "NUMBER_OF_DOORS")
    private int numberOfDoors;

    @NotNull
    @Column(name = "ENGINE_CAPACITY")
    private double engineCapacity;

    @NotNull
    @Column(name = "VEHICLE_MILEAGE")
    private BigDecimal vehicleMileage;

    @NotNull
    @Column(name = "VIN_NUMBER")
    private int vinNumber;

    @NotNull
    @Column(name = "POWER")
    private double power;

    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "VEHICLE_ID")
    private Vehicle vehicle;
}