package com.kodilla.car_rent_backend.mapper;

import com.kodilla.car_rent_backend.domain.VehicleParam;
import com.kodilla.car_rent_backend.dto.VehicleParamDto;
import com.kodilla.car_rent_backend.repository.VehicleRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class VehicleParamMapper {

        private VehicleRepository vehicleRepository;



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
                vehicleRepository.getById(vehicleParametersDto.getVehicleId())
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
                vehicleParameters.getVehicle().getId()
        );
    }

    public List<VehicleParam> mapToVehicleParamList(final List<VehicleParamDto> parameters){
        return parameters.stream()
                .map(this::mapToVehicleParam)
                .collect(Collectors.toList());
    }

    public List<VehicleParamDto> mapToVehicleParamDtoList(final List<VehicleParam> parameters){
        return parameters.stream()
                .map(this::mapToVehicleParamDto)
                .collect(Collectors.toList());
    }
}