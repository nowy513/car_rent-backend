package com.kodilla.car_rent_backend.mapper;

import com.kodilla.car_rent_backend.domain.User;
import com.kodilla.car_rent_backend.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class UserMapper {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private InvoiceMapper invoiceMapper;

    public User mapToUser(final UserDto userDto){
        return new User(
                userDto.getId(),
                userDto.getFirstName(),
                userDto.getSurName(),
                userDto.getPhone(),
                userDto.getEmail(),
                userDto.getPesel(),
                orderMapper.mapToOrderList(userDto.getOrderDtoList()),
                invoiceMapper.mapToInvoiceList(userDto.getInvoiceId())

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
                orderMapper.mapToOrderListDto(user.getOrderList()),
                invoiceMapper.mapToInvoiceListDto(user.getInvoiceList())

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