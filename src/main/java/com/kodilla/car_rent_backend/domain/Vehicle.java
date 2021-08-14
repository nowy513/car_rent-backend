package com.kodilla.car_rent_backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "VEHICLE")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VEHICLE_ID", unique = true)
    private Long id;

    @NotNull
    @Column(name = "BRAND")
    private String brand;

    @NotNull
    @Column(name = "MODEL")
    private String model;

    @NotNull
    @Column(name = "REGISTRATION")
    private String registration;

    @NotNull
    @Column(name = "AVAILABILITY")
    private Boolean availability;

    @NotNull
    @Column(name = "PRICE_PER_DAY")
    private double pricePerDay;

    @OneToMany
            (targetEntity = Order.class,
            mappedBy = "vehicle",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Order> orderList = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "VEHICLE_PARAM_ID")
    private VehicleParam vehicleParam;

}