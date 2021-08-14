package com.kodilla.car_rent_backend.mapper;

import com.kodilla.car_rent_backend.domain.Invoice;
import com.kodilla.car_rent_backend.dto.InvoiceDto;
import com.kodilla.car_rent_backend.repository.OrderRepository;
import com.kodilla.car_rent_backend.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceMapper {

    private OrderRepository orderRepository;

    private UserRepository userRepository;




    public Invoice mapToInvoice(final InvoiceDto invoiceDto){
        return new Invoice(
                invoiceDto.getId(),
                invoiceDto.getInvoiceNumber(),
                userRepository.getById(invoiceDto.getUserId()),
                orderRepository.getById(invoiceDto.getOrderDto())

        );
    }

    public InvoiceDto mapToInvoiceDto(final Invoice invoice){
        return new InvoiceDto(
                invoice.getId(),
                invoice.getInvoiceNumber(),
                invoice.getUser().getId(),
                invoice.getOrder().getId()
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