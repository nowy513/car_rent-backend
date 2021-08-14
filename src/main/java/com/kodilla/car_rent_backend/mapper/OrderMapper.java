package com.kodilla.car_rent_backend.mapper;

import com.kodilla.car_rent_backend.domain.Order;
import com.kodilla.car_rent_backend.dto.OrderDto;
import com.kodilla.car_rent_backend.repository.InvoiceRepository;
import com.kodilla.car_rent_backend.repository.UserRepository;
import com.kodilla.car_rent_backend.repository.VehicleRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class OrderMapper {


    private VehicleRepository vehicleRepository;

    private InvoiceRepository invoiceRepository;

    private UserRepository userRepository;

    public Order mapToOrder(final OrderDto orderDto) {
        return new Order(
                orderDto.getId(),
                orderDto.getDataOfRental(),
                orderDto.getDateOfReturn(),
                orderDto.getRentCost(),
                userRepository.getById(orderDto.getUserId()),
                vehicleRepository.getById(orderDto.getVehicleDto()),
                invoiceRepository.getById(orderDto.getInvoiceDto())
        );
    }

    public OrderDto mapToOrderDto(final Order order) {
        return new OrderDto(
                order.getId(),
                order.getDataOfRental(),
                order.getDateOfReturn(),
                order.getRentCost(),
                order.getUser().getId(),
                order.getVehicle().getId(),
                order.getInvoice().getId()

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