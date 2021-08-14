package com.kodilla.car_rent_backend.controller;

import com.google.gson.Gson;
import com.kodilla.car_rent_backend.domain.Vehicle;
import com.kodilla.car_rent_backend.domain.VehicleParam;
import com.kodilla.car_rent_backend.dto.VehicleParamDto;
import com.kodilla.car_rent_backend.mapper.VehicleParamMapper;
import com.kodilla.car_rent_backend.repository.VehicleParamRepository;
import com.kodilla.car_rent_backend.service.VehicleParamService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(VehicleParamController.class)
public class VehicleParamControllerTest {

    @MockBean
    VehicleParamMapper vehicleParamMapper;

    @MockBean
    VehicleParamRepository vehicleParamRepository;

    @MockBean
    VehicleParamService vehicleParamService;

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void shouldFetchEmptyVehicleParamList() throws Exception{
//        Given
        List<VehicleParamDto> vehicleParamDtos = new ArrayList<>();

        when(vehicleParamMapper.mapToVehicleParamDtoList(vehicleParamService.getAllVehicleParameters())).thenReturn(vehicleParamDtos);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                .get("/v1/vehicleParameter/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)))
                .andExpect(MockMvcResultMatchers.status().is(200));

    }

    @Test
    public void shouldGetVehicleParam() throws Exception{
//        Given
        VehicleParam vehicleParam = new VehicleParam(
                1L, "Toyota", "Rav", "Red", "Suv", "Gas", 5, 5,
                2.0, BigDecimal.valueOf(23232.0), 849382, 142.0, new Vehicle()
        );

        VehicleParamDto vehicleParamDto = new VehicleParamDto(
                1L, "Toyota", "Rav", "Red", "Suv", "Gas", 5, 5,
                2.0, BigDecimal.valueOf(23232.0), 849382, 142.0, 1L
        );

        when(vehicleParamService.getParameters(1L)).thenReturn(Optional.of(vehicleParam));
        when(vehicleParamMapper.mapToVehicleParamDto(any())).thenReturn(vehicleParamDto);
//        When & Then

        mockMvc
                .perform(MockMvcRequestBuilders
                .get("/v1/vehicleParameter/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.brand", Matchers.is("Toyota")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.model", Matchers.is("Rav")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.color", Matchers.is("Red")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.bodyType", Matchers.is("Suv")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.fuelType", Matchers.is("Gas")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.numberOfSeats", Matchers.is(5)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.numberOfDoors", Matchers.is(5)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.engineCapacity", Matchers.is(2.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.vehicleMileage", Matchers.is(23232.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.vinNumber", Matchers.is(849382)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.power", Matchers.is(142.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.vehicleId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    public void shouldGetAllVehicleParam() throws Exception{
//        Given
        List<VehicleParam> vehicleParamList = new ArrayList<>();

        when(vehicleParamService.getAllVehicleParameters()).thenReturn(vehicleParamList);

//        When & Then

        mockMvc
                .perform(MockMvcRequestBuilders
                .get("/v1/vehicleParameter/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));

    }

    @Test
    public void shouldDeleteVehicleParam() throws Exception{
//        Given
        VehicleParam vehicleParam = new VehicleParam(
                1L, "Toyota", "Rav", "Red", "Suv", "Gas", 5, 5,
                2.0, BigDecimal.valueOf(23232.0), 849382, 142.0, new Vehicle()
        );

        when(vehicleParamService.saveVehicleParameters(vehicleParam)).thenReturn(vehicleParam);

//        When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                .delete("/v1/vehicleParameter/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    public void shouldCreateVehicleParam() throws Exception {
//        Given
        VehicleParam vehicleParam = new VehicleParam(
                1L, "Toyota", "Rav", "Red", "Suv", "Gas", 5, 5,
                2.0, BigDecimal.valueOf(23232.0), 849382, 142.0, new Vehicle()
        );
        VehicleParamDto vehicleParamDto = new VehicleParamDto(
                1L, "Toyota", "Rav", "Red", "Suv", "Gas", 5, 5,
                2.0, BigDecimal.valueOf(23232.0), 849382, 142.0, 1L
        );
        when(vehicleParamService.saveVehicleParameters(any(VehicleParam.class))).thenReturn(new VehicleParam(1L, "Toyota", "Rav", "Red", "Suv", "Gas", 5, 5,
                2.0, BigDecimal.valueOf(23232.0), 849382, 142.0, new Vehicle()));

        Gson gson = new Gson();
        String jsonContent = gson.toJson(vehicleParamDto);


//        When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                .post("/v1/vehicleParameter")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
    @Test
    public void shouldUpdateVehicleParam() throws Exception {
//        Given
        VehicleParam vehicleParam = new VehicleParam(
                1L, "Toyota", "Rav", "Red", "Suv", "Gas", 5, 5,
                2.0, BigDecimal.valueOf(23232.0), 849382, 142.0, new Vehicle()
        );
        VehicleParamDto vehicleParamDto = new VehicleParamDto(
                1L, "Toyota", "Rav", "Red", "Suv", "Gas", 5, 5,
                2.0, BigDecimal.valueOf(23232.0), 849382, 142.0, 1L
        );
        VehicleParamDto vehicleParamDto2 = new VehicleParamDto(
                4L, "Update", "Update", "Red", "Suv", "Gas", 5, 5,
                2.0, BigDecimal.valueOf(23232.0), 849382, 142.0, 1L
        );

        when(vehicleParamService.saveVehicleParameters(vehicleParam)).thenReturn(new VehicleParam(4L, "Update", "Update", "Red", "Suv", "Gas", 5, 5,
                2.0, BigDecimal.valueOf(23232.0), 849382, 142.0, new Vehicle()));
        when(vehicleParamMapper.mapToVehicleParamDto(any())).thenReturn(vehicleParamDto2);
        when(vehicleParamMapper.mapToVehicleParam(any())).thenReturn(vehicleParam);

        Gson gson = new Gson();
        String jasonContent = gson.toJson(vehicleParamDto);
//        When & Then

        mockMvc
                .perform(MockMvcRequestBuilders
                .put("/v1/vehicleParameter")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jasonContent))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(4)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.brand", Matchers.is("Update")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.model", Matchers.is("Update")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.color", Matchers.is("Red")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.bodyType", Matchers.is("Suv")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.fuelType", Matchers.is("Gas")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.numberOfSeats", Matchers.is(5)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.numberOfDoors", Matchers.is(5)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.engineCapacity", Matchers.is(2.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.vehicleMileage", Matchers.is(23232.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.vinNumber", Matchers.is(849382)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.power", Matchers.is(142.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.vehicleId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}
