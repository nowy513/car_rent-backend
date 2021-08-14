package com.kodilla.car_rent_backend.controller;

import com.google.gson.Gson;
import com.kodilla.car_rent_backend.domain.Vehicle;
import com.kodilla.car_rent_backend.domain.VehicleParam;
import com.kodilla.car_rent_backend.dto.VehicleDto;
import com.kodilla.car_rent_backend.mapper.VehicleMapper;
import com.kodilla.car_rent_backend.repository.VehicleRepository;
import com.kodilla.car_rent_backend.service.VehicleService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(VehicleController.class)
public class VehicleControllerTest {

    @MockBean
    VehicleMapper vehicleMapper;

    @MockBean
    VehicleRepository vehicleRepository;

    @MockBean
    VehicleService vehicleService;

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void shouldFetchEmptyVehicleList() throws Exception{
//        Given
        List<VehicleDto> vehicleDtoList = new ArrayList<>();

        when(vehicleMapper.mapToVehicleListDto(vehicleService.getAllVehicle())).thenReturn(vehicleDtoList);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                .get("/v1/vehicle/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)))
                .andExpect(MockMvcResultMatchers.status().is(200));

    }

    @Test
    public void shouldGetVehicle() throws Exception {
//        Given
        Vehicle vehicle = new Vehicle(
                1L, "Toyota", "Rav", "RI2019", true, 30.0, new ArrayList<>(), new VehicleParam()
        );

        VehicleDto vehicleDto = new VehicleDto(
                1L, "Toyota", "Rav", "RI2019", true, 30.0, new ArrayList<>(), 1L
        );

        when(vehicleService.getVehicle(1L)).thenReturn(Optional.of(vehicle));
        when(vehicleMapper.mapToVehicleDto(any())).thenReturn(vehicleDto);

//        When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                .get("/v1/vehicle/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.brand", Matchers.is("Toyota")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.model", Matchers.is("Rav")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.registration", Matchers.is("RI2019")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.availability", Matchers.is(true)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.pricePerDay", Matchers.is(30.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.vehicleParamId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    public void shouldGetAllVehicle() throws Exception{
//        Given
        List<Vehicle> vehicleList = new ArrayList<>();

        when(vehicleService.getAllVehicle()).thenReturn(vehicleList);

//        When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                .get("/v1/vehicle/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    public void shouldDeteleVehicle() throws Exception{
//        Given
        Vehicle vehicle = new Vehicle(
                1L, "Toyota", "Rav", "RI2019", true, 30.0, new ArrayList<>(), new VehicleParam()
        );

        when(vehicleService.saveVehicle(vehicle)).thenReturn(vehicle);

//        When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                .delete("/v1/vehicle/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    public void shouldCreateVehicle() throws Exception{
//        Given
        Vehicle vehicle = new Vehicle(
                1L, "Toyota", "Rav", "RI2019", true, 30.0, new ArrayList<>(), new VehicleParam()
        );

        VehicleDto vehicleDto = new VehicleDto(
                1L, "Toyota", "Rav", "RI2019", true, 30.0, new ArrayList<>(), 1L
        );

        when(vehicleService.saveVehicle(any(Vehicle.class))).thenReturn(new Vehicle(1L, "Toyota", "Rav", "RI2019", true, 30.0, new ArrayList<>(), new VehicleParam()));

        Gson gson = new Gson();
        String jsonContent = gson.toJson(vehicleDto);


//        When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                .post("/v1/vehicle")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));

    }

    @Test
    public void shouldUpdateVehicle() throws Exception{
//        Given
        Vehicle vehicle = new Vehicle(
                1L, "Toyota", "Rav", "RI2019", true, 30.0, new ArrayList<>(), new VehicleParam()
        );

        VehicleDto vehicleDto = new VehicleDto(
                1L, "Toyota", "Rav", "RI2019", true, 30.0, new ArrayList<>(), 1L
        );
        VehicleDto vehicleDto2 = new VehicleDto(
                4L, "Update", "Update", "RI2019", true, 30.0, new ArrayList<>(), 1L
        );

        when(vehicleService.saveVehicle(vehicle)).thenReturn(new Vehicle(4L, "Update", "Update", "RI2019", true, 30.0, new ArrayList<>(), new VehicleParam()));
        when(vehicleMapper.mapToVehicleDto(any())).thenReturn(vehicleDto2);
        when(vehicleMapper.mapToVehicle(any())).thenReturn(vehicle);

        Gson gson = new Gson();
        String jasonContent = gson.toJson(vehicleDto2);

//        When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                .put("/v1/vehicle")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jasonContent))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(4)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.brand", Matchers.is("Update")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.model", Matchers.is("Update")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.registration", Matchers.is("RI2019")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.availability", Matchers.is(true)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.pricePerDay", Matchers.is(30.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.vehicleParamId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}
