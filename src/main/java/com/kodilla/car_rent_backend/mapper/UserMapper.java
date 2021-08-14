package com.kodilla.car_rent_backend.mapper;

import com.kodilla.car_rent_backend.domain.Invoice;
import com.kodilla.car_rent_backend.domain.Order;
import com.kodilla.car_rent_backend.domain.User;
import com.kodilla.car_rent_backend.dto.UserDto;
import com.kodilla.car_rent_backend.repository.InvoiceRepository;
import com.kodilla.car_rent_backend.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class UserMapper {


        private OrderRepository orderRepository;

        private InvoiceRepository invoiceRepository;


    public User mapToUser(final UserDto userDto){
        return new User(
                userDto.getId(),
                userDto.getFirstName(),
                userDto.getSurName(),
                userDto.getPhone(),
                userDto.getEmail(),
                userDto.getPesel(),
                userDto.getOrderDtoList().stream()
                .map(orderRepository::findById)
                .map(Optional::get)
                .collect(Collectors.toList()),
                userDto.getInvoiceId().stream()
                .map(invoiceRepository::findById)
                .map(Optional::get)
                .collect(Collectors.toList())
        );
    }

    public UserDto mapToUserDto(final User user){
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getSurName(),
                user.getPhone(),
                user.getEmail(),
                user.getPesel(),
                user.getOrderList().stream()
                .map(Order::getId)
                .collect(Collectors.toList()),
                user.getInvoiceList().stream()
                .map(Invoice::getId)
                .collect(Collectors.toList())
        );
    }

    public List<User> mapToUserList(final List<UserDto> users){
        return users.stream()
                .map(this::mapToUser)
                .collect(Collectors.toList());
    }

    public List<UserDto> mapToUserListDto(final List<User> users){
        return users.stream()
                .map(this::mapToUserDto)
                .collect(Collectors.toList());
    }
}