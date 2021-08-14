package com.kodilla.car_rent_backend.service;

import com.kodilla.car_rent_backend.domain.Invoice;
import com.kodilla.car_rent_backend.domain.Order;
import com.kodilla.car_rent_backend.domain.User;
import com.kodilla.car_rent_backend.repository.InvoiceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InvoiceServisTest {

    @InjectMocks
    InvoiceService invoiceService;

    @Mock
    InvoiceRepository invoiceRepository;

    @Test
    public void getAllInvoice(){
//        Given
        Invoice invoice = new Invoice(
                1L, "8472", new User(), new Order()
        );
        List<Invoice> invoiceList = new ArrayList<>();
        invoiceList.add(invoice);

        when(invoiceRepository.findAll()).thenReturn(invoiceList);

//        When
        List<Invoice> invoices = invoiceService.getAllInvoices();

//        Then
        assertEquals(1, invoices.size());
    }

    @Test
    public void getInvoice(){
//        Given
        Invoice invoice = new Invoice(
                1L, "8472", new User(), new Order()
        );
        List<Invoice> invoices = new ArrayList<>();
        invoices.add(invoice);

        when(invoiceRepository.findById(1L)).thenReturn(Optional.of(invoice));

//        When
        Optional<Invoice> invoiceOptional = invoiceService.getInvoice(1L);

//        Then
        assertEquals(Optional.of(invoice), invoiceOptional);
    }

    @Test
    public void saveInvoice(){
//        Given
        Invoice invoice = new Invoice(
                1L, "8472", new User(), new Order()
        );

        when(invoiceRepository.save(invoice)).thenReturn(invoice);

//        When
        Invoice saveTest = invoiceService.saveInvoice(invoice);

//        Then
        assertEquals("8472", saveTest.getInvoiceNumber());
    }

    @Test
    public void deleteInvoice() {
//        Given
        Invoice invoice = new Invoice(
                1L, "8472", new User(), new Order()
        );
        Long id = invoice.getId();

        invoiceService.deleteInvoice(id);

//        When
        Optional<Invoice> invoiceOptional = invoiceService.getInvoice(1L);

//        Then
        assertFalse(invoiceOptional.isPresent());
    }
}
