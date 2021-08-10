package com.kodilla.car_rent_backend.repository;

import com.kodilla.car_rent_backend.domain.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle,Long> {
}
