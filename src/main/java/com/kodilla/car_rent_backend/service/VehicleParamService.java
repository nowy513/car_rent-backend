package com.kodilla.car_rent_backend.service;

import com.kodilla.car_rent_backend.domain.VehicleParam;
import com.kodilla.car_rent_backend.repository.VehicleParamRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VehicleParamService {

    private VehicleParamRepository vehicleParamRepository;

    public List<VehicleParam> getAllVehicleParameters(){
        return vehicleParamRepository.findAll();
    }

    public VehicleParam saveVehicleParameters(VehicleParam vehicleParameters){
        return vehicleParamRepository.save(vehicleParameters);
    }

    public Optional<VehicleParam> getParameters(final Long id){
        return vehicleParamRepository.findById(id);
    }

    public void deleteVehicleParameters(final Long id){
        vehicleParamRepository.deleteById(id);
    }
}