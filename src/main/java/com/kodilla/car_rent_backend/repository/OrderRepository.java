package com.kodilla.car_rent_backend.repository;

import com.kodilla.car_rent_backend.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
