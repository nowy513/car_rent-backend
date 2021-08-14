package com.kodilla.car_rent_backend.service;

import com.kodilla.car_rent_backend.domain.Vehicle;
import com.kodilla.car_rent_backend.domain.VehicleParam;
import com.kodilla.car_rent_backend.repository.VehicleParamRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VehicleParamServiceTest {

    @InjectMocks
    VehicleParamService vehicleParamService;

    @Mock
    VehicleParamRepository vehicleParamRepository;

    @Test
    public void getAllVehicleParam(){
//        Given
        VehicleParam vehicleParam = new VehicleParam(
                1L, "Toyota", "Rav", "Red", "Suv", "Gas", 5, 5,
                2.0, BigDecimal.valueOf(23232.0), 849382, 142.0, new Vehicle()
        );
        List<VehicleParam> vehicleParamList = new ArrayList<>();
        vehicleParamList.add(vehicleParam);

        when(vehicleParamRepository.findAll()).thenReturn(vehicleParamList);

//        When
        List<VehicleParam> vehicleParams = vehicleParamService.getAllVehicleParameters();

//        Then
        assertEquals(1, vehicleParams.size());
    }

    @Test
    public void getVehicleParam() {
//        Given
        VehicleParam vehicleParam = new VehicleParam(
                1L, "Toyota", "Rav", "Red", "Suv", "Gas", 5, 5,
                2.0, BigDecimal.valueOf(23232.0), 849382, 142.0, new Vehicle()
        );
        List<VehicleParam> vehicleParams = new ArrayList<>();
        vehicleParams.add(vehicleParam);

        when(vehicleParamRepository.findById(1L)).thenReturn(Optional.of(vehicleParam));

//        When
        Optional<VehicleParam> vehicleParamOptional = vehicleParamService.getParameters(1L);

//        Then
        assertEquals(Optional.of(vehicleParam), vehicleParamOptional);
    }

    @Test
    public void saveVehicleParam() {
//        Given
        VehicleParam vehicleParam = new VehicleParam(
                1L, "Toyota", "Rav", "Red", "Suv", "Gas", 5, 5,
                2.0, BigDecimal.valueOf(23232.0), 849382, 142.0, new Vehicle()
        );

        when(vehicleParamRepository.save(vehicleParam)).thenReturn(vehicleParam);

//        When
        VehicleParam saveTest = vehicleParamService.saveVehicleParameters(vehicleParam);

//        Then
        assertEquals("Suv", saveTest.getBodyType());
        assertEquals("Gas", saveTest.getFuelType());
    }

    @Test
    public void deleteVehicleParam() {
//    Given
        VehicleParam vehicleParam = new VehicleParam(
                1L, "Toyota", "Rav", "Red", "Suv", "Gas", 5, 5,
                2.0, BigDecimal.valueOf(23232.0), 849382, 142.0, new Vehicle()
        );
        Long id = vehicleParam.getId();

        vehicleParamService.deleteVehicleParameters(id);

//        When
        Optional<VehicleParam> vehicleParamOptional = vehicleParamService.getParameters(1L);

//        Then
        assertFalse(vehicleParamOptional.isPresent());
    }
}
