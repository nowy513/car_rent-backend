package com.kodilla.car_rent_backend.controller;

import com.kodilla.car_rent_backend.dto.OrderDto;
import com.kodilla.car_rent_backend.exception.OrderNotFoundException;
import com.kodilla.car_rent_backend.mapper.OrderMapper;
import com.kodilla.car_rent_backend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderMapper orderMapper;

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public List<OrderDto> getOrders(){
        return orderMapper.mapToOrderListDto(orderService.getAllOrders());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{orderId}")
    public OrderDto getOrder(@PathVariable Long orderId) throws OrderNotFoundException {
        return orderMapper.mapToOrderDto(orderService.getOrder(orderId).orElseThrow(OrderNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    public void createOrder(@RequestBody OrderDto orderDto) {
        orderService.saveOrder(orderMapper.mapToOrder(orderDto));
    }

    @RequestMapping(method = RequestMethod.PUT)
    public OrderDto updateOrder(@RequestBody OrderDto orderDto){
        return orderMapper.mapToOrderDto(orderService.saveOrder(orderMapper.mapToOrder(orderDto)));
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "/{orderId}")
    public void deleteOrder(@PathVariable Long orderId){
        orderService.deleteOrder(orderId);
    }
}