package com.kodilla.car_rent_backend.mapper;

import com.kodilla.car_rent_backend.domain.VehicleParam;
import com.kodilla.car_rent_backend.dto.VehicleParamDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class VehicleParamMapper {

    private VehicleMapper vehicleMapper;

    public VehicleParam mapToVehicleParam(final VehicleParamDto vehicleParametersDto){
        return new VehicleParam(
                vehicleParametersDto.getId(),
                vehicleParametersDto.getBrand(),
                vehicleParametersDto.getModel(),
                vehicleParametersDto.getColor(),
                vehicleParametersDto.getBodyType(),
                vehicleParametersDto.getFuelType(),
                vehicleParametersDto.getNumberOfSeats(),
                vehicleParametersDto.getNumberOfDoors(),
                vehicleParametersDto.getEngineCapacity(),
                vehicleParametersDto.getVehicleMileage(),
                vehicleParametersDto.getVinNumber(),
                vehicleParametersDto.getPower(),
                vehicleMapper.mapToVehicle(vehicleParametersDto.getVehicleId())
        );
    }

    public VehicleParamDto mapToVehicleParamDto(final VehicleParam vehicleParameters){
        return new VehicleParamDto(
                vehicleParameters.getId(),
                vehicleParameters.getBrand(),
                vehicleParameters.getModel(),
                vehicleParameters.getColor(),
                vehicleParameters.getBodyType(),
                vehicleParameters.getFuelType(),
                vehicleParameters.getNumberOfSeats(),
                vehicleParameters.getNumberOfDoors(),
                vehicleParameters.getEngineCapacity(),
                vehicleParameters.getVehicleMileage(),
                vehicleParameters.getVinNumber(),
                vehicleParameters.getPower(),
                vehicleMapper.mapToVehicleDto(vehicleParameters.getVehicle())
        );
    }

    public List<VehicleParam> mapToVehicleParam(final List<VehicleParamDto> parameters){
        return parameters.stream()
                .map(this::mapToVehicleParam)
                .collect(Collectors.toList());
    }

    public List<VehicleParamDto> mapToVehicleParamDto(final List<VehicleParam> parameters){
        return parameters.stream()
                .map(this::mapToVehicleParamDto)
                .collect(Collectors.toList());
    }
}