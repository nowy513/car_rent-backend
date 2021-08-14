package com.kodilla.car_rent_backend.service;

import com.kodilla.car_rent_backend.domain.Invoice;
import com.kodilla.car_rent_backend.domain.Order;
import com.kodilla.car_rent_backend.domain.User;
import com.kodilla.car_rent_backend.domain.Vehicle;
import com.kodilla.car_rent_backend.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @InjectMocks
    OrderService orderService;

    @Mock
    OrderRepository orderRepository;

    @Test
    public void getAllOrder(){
        Order order = new Order(
                1L, LocalDate.of(2021,03,20),
                LocalDate.of(2021,03,28),
                20.0, new User(), new Vehicle(), new Invoice()
        );
        List<Order> orderList = new ArrayList<>();
        orderList.add(order);

        when(orderRepository.findAll()).thenReturn(orderList);

//        When
        List<Order> orders = orderService.getAllOrders();

//        Then
        assertEquals(1, orders.size());
    }

    @Test
    public void getOrder(){
//        Given
        Order order = new Order(
                1L, LocalDate.of(2021,03,20),
                LocalDate.of(2021,03,28),
                20.0, new User(), new Vehicle(), new Invoice()
        );
        List<Order> orders = new ArrayList<>();
        orders.add(order);

        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

//        When
        Optional<Order> orderOptional = orderService.getOrder(1L);

//        Then
        assertEquals(Optional.of(order), orderOptional);
    }

    @Test
    public void saveOrder(){
//        Given
        Order order = new Order(
                1L, LocalDate.of(2021,03,20),
                LocalDate.of(2021,03,28),
                20.0, new User(), new Vehicle(), new Invoice()
        );

        when(orderRepository.save(order)).thenReturn(order);

//        When
        Order saveTest = orderService.saveOrder(order);

//        Then
        assertEquals(order.getId(), saveTest.getId());
    }

    @Test
    public void deleteOrder(){
//        Given
        Order order = new Order(
                1L, LocalDate.of(2021,03,20),
                LocalDate.of(2021,03,28),
                20.0, new User(), new Vehicle(), new Invoice()
        );
        Long id = order.getId();

        orderService.deleteOrder(id);

//        When
        Optional<Order> orderOptional = orderService.getOrder(1L);

//        Then
        assertFalse(orderOptional.isPresent());
    }
}
