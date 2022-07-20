package com.jpsols.jpapp.controller;

import com.jpsols.jpapp.dto.PaymentResponse;
import com.jpsols.jpapp.repository.PaymentRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import com.jpsols.jpapp.entity.Payment;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
public class InvoiceController {

    @Autowired
    private PaymentRepository repository;
    @GetMapping(value = "/customers/invoices/invsCust{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<byte[]> downloadInvoices(@PathVariable Integer id) throws IOException, JRException {

        List<Payment> payments= repository.findPaymentsByPid(id);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(payments);

        Map<String,Object> parameters=new HashMap<>();
        parameters.put("createdby","JPSOLS");

//        File file = ResourceUtils.getFile("classpath:invoices.jrxml");
        ClassPathResource classPathResource = new ClassPathResource("invoices.jrxml");
        InputStream inputStream = classPathResource.getInputStream();

        JasperReport jasperReport = JasperCompileManager
                .compileReport(inputStream);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        byte data[] = JasperExportManager.exportReportToPdf(jasperPrint);

        System.err.println(data);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");


        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);

    }

    @GetMapping(value = "/customers/invoices/invs{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<byte[]> downloadInvoiceById(@PathVariable Integer id) throws IOException, JRException {

        List<Payment> payments= repository.findInvoiceByPid(id);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(payments);

        Map<String,Object> parameters=new HashMap<>();
        parameters.put("createdby","JPSOLS");

        ClassPathResource classPathResource = new ClassPathResource("invoiceById.jrxml");
        InputStream inputStream = classPathResource.getInputStream();

        JasperReport jasperReport = JasperCompileManager
                .compileReport(inputStream);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        byte data[] = JasperExportManager.exportReportToPdf(jasperPrint);

        System.err.println(data);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");


        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);

    }
}
