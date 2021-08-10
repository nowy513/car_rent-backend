package com.kodilla.car_rent_backend.service;

import com.kodilla.car_rent_backend.domain.Order;
import com.kodilla.car_rent_backend.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderService {

    private OrderRepository orderRepository;

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public Order saveOrder(final Order order){
        return orderRepository.save(order);
    }

    public Optional<Order> getOrder(final Long id){
        return orderRepository.findById(id);
    }

    public void deleteOrder(final Long id){
        orderRepository.deleteById(id);
    }
}