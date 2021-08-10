package com.kodilla.car_rent_backend.mapper;

import com.kodilla.car_rent_backend.domain.Order;
import com.kodilla.car_rent_backend.dto.OrderDto;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class OrderMapper {

    @Autowired
    private VehicleMapper vehicleMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private InvoiceMapper invoiceMapper;

    public Order mapToOrder(final OrderDto orderDto) {
        return new Order(
                orderDto.getId(),
                orderDto.getDataOfRental(),
                orderDto.getDateOfReturn(),
                orderDto.getRentCost(),
                userMapper.mapToUser(orderDto.getUserId()),
                vehicleMapper.mapToVehicle(orderDto.getVehicleDto()),
                invoiceMapper.mapToInvoice(orderDto.getInvoiceDto())

        );
    }

    public OrderDto mapToOrderDto(final Order order) {
        return new OrderDto(
                order.getId(),
                order.getDataOfRental(),
                order.getDateOfReturn(),
                order.getRentCost(),
                userMapper.mapToUserDto(order.getUser()),
                vehicleMapper.mapToVehicleDto(order.getVehicle()),
                invoiceMapper.mapToInvoiceDto(order.getInvoice())

        );
    }

    public List<Order> mapToOrderList(final List<OrderDto> orders){
        return orders.stream()
                .map(this::mapToOrder)
                .collect(Collectors.toList());
    }

    public List<OrderDto> mapToOrderListDto(final List<Order> orders){
        return orders.stream()
                .map(this::mapToOrderDto)
                .collect(Collectors.toList());
    }

}