package com.kodilla.car_rent_backend.mapper;

import com.kodilla.car_rent_backend.domain.Vehicle;
import com.kodilla.car_rent_backend.dto.VehicleDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleMapper {

    private OrderMapper orderMapper;
    private VehicleParamMapper vehicleParamMapper;

    public Vehicle mapToVehicle(final VehicleDto vehicleDto) {
        return new Vehicle(
                vehicleDto.getId(),
                vehicleDto.getBrand(),
                vehicleDto.getModel(),
                vehicleDto.getRegistration(),
                vehicleDto.getAvailability(),
                vehicleDto.getPricePerDay(),
                orderMapper.mapToOrderList(vehicleDto.getOrderDtoList()),
                vehicleParamMapper.mapToVehicleParam(vehicleDto.getVehicleParamId())
        );
    }

    public VehicleDto mapToVehicleDto(final Vehicle vehicle) {
        return new VehicleDto(
                vehicle.getId(),
                vehicle.getBrand(),
                vehicle.getModel(),
                vehicle.getRegistration(),
                vehicle.getAvailability(),
                vehicle.getPricePerDay(),
                vehicleParamMapper.mapToVehicleParamDto(vehicle.getVehicleParam()),
                orderMapper.mapToOrderListDto(vehicle.getOrderList())
        );
    }

    public List<Vehicle> mapToVehicleList(final List<VehicleDto> vehicles){
        return vehicles.stream()
                .map(this::mapToVehicle)
                .collect(Collectors.toList());
    }

    public List<VehicleDto> mapToVehicleListDto(final List<Vehicle> vehicles){
        return vehicles.stream()
                .map(this::mapToVehicleDto)
                .collect(Collectors.toList());
    }
}