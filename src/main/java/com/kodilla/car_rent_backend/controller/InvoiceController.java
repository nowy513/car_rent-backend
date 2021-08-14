package com.kodilla.car_rent_backend.controller;

import com.kodilla.car_rent_backend.dto.InvoiceDto;
import com.kodilla.car_rent_backend.exception.InvoiceNotFoundException;
import com.kodilla.car_rent_backend.mapper.InvoiceMapper;
import com.kodilla.car_rent_backend.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private InvoiceMapper invoiceMapper;

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public List<InvoiceDto> getInvoices(){
        return invoiceMapper.mapToInvoiceListDto(invoiceService.getAllInvoices());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{invoiceId}")
    public InvoiceDto getInvoice(@PathVariable Long invoiceId) throws InvoiceNotFoundException {
        return invoiceMapper.mapToInvoiceDto(invoiceService.getInvoice(invoiceId).orElseThrow(InvoiceNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    public void createInvoice(@RequestBody InvoiceDto invoiceDto){
        invoiceService.saveInvoice(invoiceMapper.mapToInvoice(invoiceDto));
    }

    @RequestMapping(method = RequestMethod.PUT)
    public InvoiceDto updateInvoice(@RequestBody InvoiceDto invoiceDto){
        return invoiceMapper.mapToInvoiceDto(invoiceService.saveInvoice(invoiceMapper.mapToInvoice(invoiceDto)));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{invoiceId}")
    public void deteleInvoice(@PathVariable Long invoiceId){
        invoiceService.deleteInvoice(invoiceId);
    }

}