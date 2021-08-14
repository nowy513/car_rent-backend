package com.kodilla.car_rent_backend.service;

import com.kodilla.car_rent_backend.domain.Vehicle;
import com.kodilla.car_rent_backend.domain.VehicleParam;
import com.kodilla.car_rent_backend.repository.VehicleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VehicleServiceTest {

    @InjectMocks
    VehicleService vehicleService;

    @Mock
    VehicleRepository vehicleRepository;


    @Test
    public void getAllVehicle() {
//    Given
        Vehicle vehicle = new Vehicle(
                1L, "Toyota", "Rav", "RI2019", true, 30.0, new ArrayList<>(), new VehicleParam()
        );
        List<Vehicle> vehicleList = new ArrayList<>();
        vehicleList.add(vehicle);

        when(vehicleRepository.findAll()).thenReturn(vehicleList);

//        When
        List<Vehicle> vehicles = vehicleService.getAllVehicle();

//        Then
        assertEquals(1, vehicles.size());
    }

    @Test
    public void getVehicle(){
//        Given
        Vehicle vehicle = new Vehicle(
                1L, "Toyota", "Rav", "RI2019", true, 30.0, new ArrayList<>(), new VehicleParam()
        );
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(vehicle);

        when(vehicleRepository.findById(1L)).thenReturn(Optional.of(vehicle));

//        When
        Optional<Vehicle> vehicleOptional = vehicleService.getVehicle(1L);

//        Then
        assertEquals(Optional.of(vehicle), vehicleOptional);
    }

    @Test
    public void saveVehicle(){
//        Given
        Vehicle vehicle = new Vehicle(
                1L, "Toyota", "Rav", "RI2019", true, 30.0, new ArrayList<>(), new VehicleParam()
        );

        when(vehicleRepository.save(vehicle)).thenReturn(vehicle);

//        When
        Vehicle saveTest = vehicleService.saveVehicle(vehicle);
//        Then
        assertEquals("Toyota", saveTest.getBrand());
        assertEquals("RI2019", saveTest.getRegistration());
    }

    @Test
    public void deleteVehicle(){
//        Given
        Vehicle vehicle = new Vehicle(
                1L, "Toyota", "Rav", "RI2019", true, 30.0, new ArrayList<>(), new VehicleParam()
        );

        Long id = vehicle.getId();

//        When
        Optional<Vehicle> vehicleOptional = vehicleService.getVehicle(1L);

//        Then
        assertFalse(vehicleOptional.isPresent());
    }

}
