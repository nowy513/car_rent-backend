package com.kodilla.car_rent_backend.controller;

import com.kodilla.car_rent_backend.domain.VehicleParam;
import com.kodilla.car_rent_backend.dto.VehicleParamDto;
import com.kodilla.car_rent_backend.exception.VehicleParamNotFoundException;
import com.kodilla.car_rent_backend.mapper.VehicleParamMapper;
import com.kodilla.car_rent_backend.service.VehicleParamService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/vehicleParameter")
public class VehicleParamController {

    @Autowired
    private VehicleParamMapper vehicleParamMapper;
    @Autowired
    private VehicleParamService vehicleParamService;

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public List<VehicleParamDto> getVehicleParam(){
        return vehicleParamMapper.mapToVehicleParamDtoList(vehicleParamService.getAllVehicleParameters());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{vehicleParamId}")
    public VehicleParamDto getParameters(@PathVariable Long vehicleParamId)throws VehicleParamNotFoundException {
        return vehicleParamMapper.mapToVehicleParamDto(vehicleParamService.getParameters(vehicleParamId).orElseThrow(VehicleParamNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    public void createParameters(@RequestBody VehicleParamDto vehicleParametersDto){
        VehicleParam vehicleParameters = vehicleParamMapper.mapToVehicleParam(vehicleParametersDto);
        vehicleParamService.saveVehicleParameters(vehicleParameters);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public VehicleParamDto updateParameters(@RequestBody VehicleParamDto vehicleParametersDto){
        return vehicleParamMapper.mapToVehicleParamDto(vehicleParamService.saveVehicleParameters(vehicleParamMapper.mapToVehicleParam(vehicleParametersDto)));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{vehicleParamId}")
    public void deleteVehicleParam(@PathVariable Long vehicleParamId){
        vehicleParamService.deleteVehicleParameters(vehicleParamId);
    }
}