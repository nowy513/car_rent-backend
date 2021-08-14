package com.kodilla.car_rent_backend.controller;

import com.kodilla.car_rent_backend.domain.Invoice;
import com.kodilla.car_rent_backend.domain.Order;
import com.kodilla.car_rent_backend.domain.User;
import com.kodilla.car_rent_backend.dto.InvoiceDto;
import com.kodilla.car_rent_backend.mapper.InvoiceMapper;
import com.kodilla.car_rent_backend.repository.InvoiceRepository;
import com.kodilla.car_rent_backend.service.InvoiceService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(InvoiceController.class)
public class InvoiceCotrollerTest {

    @MockBean
    InvoiceRepository invoiceRepository;

    @MockBean
    InvoiceMapper invoiceMapper;

    @MockBean
    InvoiceService invoiceService;

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void shouldFetchEmptyInvoiceList() throws Exception{
//        Given
        List<InvoiceDto> invoiceDtoList = new ArrayList<>();

        when(invoiceMapper.mapToInvoiceListDto(invoiceService.getAllInvoices())).thenReturn(invoiceDtoList);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                .get("/v1/invoice/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)))
                .andExpect(MockMvcResultMatchers.status().is(200));

    }

    @Test
    public void shouldGetInvoice() throws Exception{
//        Given
        Invoice invoice = new Invoice(
                1L, "8472", new User(), new Order()
        );
        InvoiceDto invoiceDto = new InvoiceDto(
                1L, "8472", 1L, 1L
        );

        when(invoiceService.getInvoice(1L)).thenReturn(Optional.of(invoice));
        when(invoiceMapper.mapToInvoiceDto(any())).thenReturn(invoiceDto);

//        When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                .get("/v1/invoice/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.invoiceNumber", Matchers.is("8472")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.orderDto", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    public void shouldGetAllInvoice() throws Exception{
//        Given
        List<Invoice> invoiceList = new ArrayList<>();
        when(invoiceService.getAllInvoices()).thenReturn(invoiceList);

        //        When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                .get("/v1/invoice/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    public void shouldDeleteInvoice() throws Exception {
//        Given
        Invoice invoice = new Invoice(
                1L, "8472", new User(), new Order()
        ) ;

        when(invoiceService.saveInvoice(invoice)).thenReturn(invoice);

//        When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                .delete("/v1/invoice/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    public void shouldCreateInvoice() throws Exception{
//        Given
        Invoice invoice = new Invoice(
                1L, "8472", new User(), new Order()
        );
        InvoiceDto invoiceDto = new InvoiceDto(
                1L, "8472", 1L, 1L
        );

        when(invoiceService.saveInvoice(any(Invoice.class))).thenReturn(new Invoice(1L, "8472", new User(), new Order()));

        Gson gson = new Gson();
        String jsonContent = gson.toJson(invoiceDto);

//        When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                .post("/v1/invoice")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));

    }

    @Test
    public void shouldUpdateInvoice() throws Exception{
//        Given
        Invoice invoice = new Invoice(
                1L, "8472", new User(), new Order()
        );
        InvoiceDto invoiceDto = new InvoiceDto(
                1L, "8472", 1L, 1L
        );
        InvoiceDto invoiceDto2 = new InvoiceDto(
                4L, "Update", 1L, 1L
        );

        when(invoiceService.saveInvoice(invoice)).thenReturn(new Invoice(4L, "Update", new User(), new Order()));
        when(invoiceMapper.mapToInvoiceDto(any())).thenReturn(invoiceDto2);
        when(invoiceMapper.mapToInvoice(any())).thenReturn(invoice);

        Gson gson = new Gson();
        String jasonContent = gson.toJson(invoiceDto);

        //        When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                .put("/v1/invoice")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jasonContent))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(4)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.invoiceNumber", Matchers.is("Update")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.orderDto", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

}
