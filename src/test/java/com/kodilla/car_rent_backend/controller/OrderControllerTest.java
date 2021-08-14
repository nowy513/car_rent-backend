package com.kodilla.car_rent_backend.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kodilla.car_rent_backend.domain.Invoice;
import com.kodilla.car_rent_backend.domain.Order;
import com.kodilla.car_rent_backend.domain.User;
import com.kodilla.car_rent_backend.domain.Vehicle;
import com.kodilla.car_rent_backend.dto.OrderDto;
import com.kodilla.car_rent_backend.localDateAdapter.LocalDateAdapter;
import com.kodilla.car_rent_backend.mapper.OrderMapper;
import com.kodilla.car_rent_backend.repository.OrderRepository;
import com.kodilla.car_rent_backend.service.OrderService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @MockBean
    OrderMapper orderMapper;

    @MockBean
    OrderRepository orderRepository;

    @MockBean
    OrderService orderService;

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void shouldFetchEmptyOrderList() throws Exception{
//        Given
        List<OrderDto> orderDtoList = new ArrayList<>();

        when(orderMapper.mapToOrderListDto(orderService.getAllOrders())).thenReturn(orderDtoList);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/order/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)))
                .andExpect(MockMvcResultMatchers.status().is(200));

    }

    @Test
    public void shouldGetOrder() throws Exception{
//        Given
        Order order = new Order(
                1L, LocalDate.of(2021,03,20),
                LocalDate.of(2021,03,28),
                20.0, new User(), new Vehicle(), new Invoice()
        );

        OrderDto orderDto = new OrderDto(
                1L, LocalDate.of(2021,03,20),
                LocalDate.of(2021,03,28),
                20.0, 1L, 1L, 1L
        );

        when(orderService.getOrder(1L)).thenReturn(Optional.of(order));
        when(orderMapper.mapToOrderDto(any())).thenReturn(orderDto);

//        When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                .get("/v1/order/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dataOfRental", Matchers.is(LocalDate.of(2021,03,20).toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dateOfReturn", Matchers.is(LocalDate.of(2021, 03, 28).toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rentCost", Matchers.is(20.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.vehicleDto", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.invoiceDto", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.status().is(200));



    }

    @Test
    public void shouldGetAllOrder() throws Exception{
        //        Given
        List<Order> orderList = new ArrayList<>();
        when(orderService.getAllOrders()).thenReturn(orderList);

//        When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                .get("/v1/order/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));

    }

    @Test
    public void shouldDeleteOrder() throws Exception {
//        Given
        Order order = new Order(
                1L, LocalDate.of(2021,03,20),
                LocalDate.of(2021,03,28),
                20.0, new User(), new Vehicle(), new Invoice()
        );

        when(orderService.saveOrder(order)).thenReturn(order);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                .delete("/v1/order/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    public void shouldCreateOrder() throws Exception{
//        Given
        Order order = new Order(
                1L, LocalDate.of(2021,3,20), LocalDate.of(2021,3,28), 20.0, new User(), new Vehicle(), new Invoice()
        );
        OrderDto orderDto = new OrderDto(
                1L, LocalDate.of(2021,3,20), LocalDate.of(2021,3,28), 20.0, 1L, 1L, 1L
        );

        when(orderService.saveOrder(any(Order.class))).thenReturn(new Order(1L, LocalDate.of(2021,3,20), LocalDate.of(2021,3,28), 20.0, new User(), new Vehicle(), new Invoice()));

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        String jsonContent = gson.toJson(orderDto);


//        When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                .post("/v1/order")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    public void shouldUpdateOrder()throws Exception {
//        Given
        Order order = new Order(
                1L, LocalDate.of(2021,03,20),
                LocalDate.of(2021,03,28),
                20.0, new User(), new Vehicle(), new Invoice()
        );

        OrderDto orderDto = new OrderDto(
                1L, LocalDate.of(2021,03,20),
                LocalDate.of(2021,03,28),
                20.0, 1L, 1L, 1L
        );
        OrderDto orderDto2 = new OrderDto(
                4L, LocalDate.of(2021,03,20),
                LocalDate.of(2021,03,28),
                20.0, 1L, 1L, 1L
        );

        when(orderService.saveOrder(order)).thenReturn(new Order(4L, LocalDate.of(2021,03,20),
                LocalDate.of(2021,03,28),
                20.0, new User(), new Vehicle(), new Invoice()));
        when(orderMapper.mapToOrderDto(any())).thenReturn(orderDto2);
        when(orderMapper.mapToOrder(any())).thenReturn(order);

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        String jasonContent = gson.toJson(orderDto);

//        When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                .put("/v1/order")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jasonContent))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(4)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dataOfRental", Matchers.is(LocalDate.of(2021,03,20).toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dateOfReturn", Matchers.is(LocalDate.of(2021, 03, 28).toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rentCost", Matchers.is(20.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.vehicleDto", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.invoiceDto", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

}
