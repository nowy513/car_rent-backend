package com.kodilla.car_rent_backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "USER")
public class User {


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;

    @Column(name = "FIRSTNAME")
    @NotNull
    private String firstName;

    @Column(name = "SURNAME")
    @NotNull
    private String surName;

    @Column(name = "PHONE")
    @NotNull
    private int phone;

    @Column(name = "EMAIL")
    @NotNull
    private String email;

    @Column(name = "PESEL")
    @NotNull
    private int pesel;

    @OneToMany(
            targetEntity = Order.class,
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Order> orderList = new ArrayList<>();


    @OneToMany(
            targetEntity = Invoice.class,
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Invoice> invoiceList = new ArrayList<>();
}