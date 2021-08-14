package com.kodilla.car_rent_backend.mapper;

import com.kodilla.car_rent_backend.domain.Order;
import com.kodilla.car_rent_backend.domain.Vehicle;
import com.kodilla.car_rent_backend.dto.VehicleDto;
import com.kodilla.car_rent_backend.repository.OrderRepository;
import com.kodilla.car_rent_backend.repository.VehicleParamRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class VehicleMapper {

    private OrderRepository orderRepository;

    private VehicleParamRepository vehicleParamRepository;


    public Vehicle mapToVehicle(final VehicleDto vehicleDto) {
        return new Vehicle(
                vehicleDto.getId(),
                vehicleDto.getBrand(),
                vehicleDto.getModel(),
                vehicleDto.getRegistration(),
                vehicleDto.getAvailability(),
                vehicleDto.getPricePerDay(),
                vehicleDto.getOrderDtoList().stream()
                        .map(orderRepository::findById)
                        .map(Optional::get)
                        .collect(Collectors.toList()),
                vehicleParamRepository.getById(vehicleDto.getVehicleParamId())
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
                vehicle.getOrderList().stream()
                .map(Order::getId)
                .collect(Collectors.toList()),
                vehicle.getVehicleParam().getId()
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