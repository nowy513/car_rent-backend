package com.kodilla.car_rent_backend.mapper;
import com.kodilla.car_rent_backend.domain.Invoice;
import com.kodilla.car_rent_backend.dto.InvoiceDto;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class InvoiceMapper {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private UserMapper userMapper;


    public Invoice mapToInvoice(final InvoiceDto invoiceDto){
        return new Invoice(
                invoiceDto.getId(),
                invoiceDto.getInvoiceNumber(),
                userMapper.mapToUser(invoiceDto.getUserId()),
                orderMapper.mapToOrder(invoiceDto.getOrderDto())
        );
    }

    public InvoiceDto mapToInvoiceDto(final Invoice invoice){
        return new InvoiceDto(
                invoice.getId(),
                invoice.getInvoiceNumber(),
                userMapper.mapToUserDto(invoice.getUser()),
                orderMapper.mapToOrderDto(invoice.getOrder())
        );
    }

    public List<Invoice> mapToInvoiceList(final List<InvoiceDto> invoices){
        return invoices.stream()
                .map(this::mapToInvoice)
                .collect(Collectors.toList());
    }

    public List<InvoiceDto> mapToInvoiceListDto(final List<Invoice> invoices){
        return invoices.stream()
                .map(this::mapToInvoiceDto)
                .collect(Collectors.toList());
    }


}