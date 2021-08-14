package com.kodilla.car_rent_backend.service;

import com.kodilla.car_rent_backend.domain.Invoice;
import com.kodilla.car_rent_backend.repository.InvoiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class InvoiceService {

    private InvoiceRepository invoiceRepository;

    public List<Invoice> getAllInvoices(){
        return invoiceRepository.findAll();
    }

    public Invoice saveInvoice(final Invoice invoice){
        return invoiceRepository.save(invoice);
    }

    public Optional<Invoice> getInvoice(final Long id) {
        return invoiceRepository.findById(id);
    }

    public void deleteInvoice(final Long id){
        invoiceRepository.deleteById(id);
    }
}