package com.kodilla.car_rent_backend.service;

import com.kodilla.car_rent_backend.domain.Vehicle;
import com.kodilla.car_rent_backend.repository.VehicleRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VehicleService {

    private VehicleRepository vehicleRepository;

    public List<Vehicle> getAllVehicle(){
        return vehicleRepository.findAll();
    }

    public Vehicle saveVehicle(final Vehicle vehicle){
        return vehicleRepository.save(vehicle);
    }

    public Optional<Vehicle> getVehicle(final Long id){
        return vehicleRepository.findById(id);
    }

    public void deleteVehicle(final Long id){
        vehicleRepository.deleteById(id);
    }
}