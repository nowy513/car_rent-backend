package com.kodilla.car_rent_backend.repository;

import com.kodilla.car_rent_backend.domain.VehicleParam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleParamRepository extends JpaRepository<VehicleParam, Long> {
}
