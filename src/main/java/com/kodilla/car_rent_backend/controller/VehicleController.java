package com.kodilla.car_rent_backend.controller;

import com.kodilla.car_rent_backend.dto.VehicleDto;
import com.kodilla.car_rent_backend.exception.VehicleNotFoundException;
import com.kodilla.car_rent_backend.mapper.VehicleMapper;
import com.kodilla.car_rent_backend.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/vehicle")
public class VehicleController {

    @Autowired
    private VehicleMapper vehicleMapper;
    @Autowired
    private VehicleService vehicleService;

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public List<VehicleDto> getVehicles(){
        return vehicleMapper.mapToVehicleListDto(vehicleService.getAllVehicle());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{vehicleId}")
    public VehicleDto getVehicle(@PathVariable Long vehicleId) throws VehicleNotFoundException {
        return vehicleMapper.mapToVehicleDto(vehicleService.getVehicle(vehicleId).orElseThrow(VehicleNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    public void createVehicle(@RequestBody VehicleDto vehicleDto){
        vehicleService.saveVehicle(vehicleMapper.mapToVehicle(vehicleDto));
    }

    @RequestMapping(method = RequestMethod.PUT)
    public VehicleDto updateVehicle(@RequestBody VehicleDto vehicleDto){
        return vehicleMapper.mapToVehicleDto(vehicleService.saveVehicle(vehicleMapper.mapToVehicle(vehicleDto)));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{vehicleId}")
    public void deleteVehicle(@PathVariable Long vehicleId){
        vehicleService.deleteVehicle(vehicleId);
    }


}