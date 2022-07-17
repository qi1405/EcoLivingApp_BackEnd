package com.jpsols.jpapp.controller;

import com.jpsols.jpapp.repository.PaymentRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import com.jpsols.jpapp.entity.Payment;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
public class InvoiceController {

    @Autowired
    private PaymentRepository repository;
    @GetMapping(value = "/customers/invoices/invsCust{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> downloadInvoices(@PathVariable Integer id) throws FileNotFoundException, JRException {

        List<Payment> payments= repository.findPaymentsByPid(id);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(payments);

        Map<String,Object> parameters=new HashMap<>();
        parameters.put("createdby","JPSOLS");

        File file = ResourceUtils.getFile("classpath:invoices.jrxml");

        JasperReport jasperReport = JasperCompileManager
                .compileReport(new FileInputStream(file.getAbsolutePath()));

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        byte data[] = JasperExportManager.exportReportToPdf(jasperPrint);

        System.err.println(data);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");


        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);

    }

    @GetMapping(value = "/customers/invoices/invs{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> downloadInvoiceById(@PathVariable Integer id) throws FileNotFoundException, JRException {

        List<Payment> payments= repository.findInvoiceByPid(id);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(payments);

        Map<String,Object> parameters=new HashMap<>();
        parameters.put("createdby","JPSOLS");

        File file = ResourceUtils.getFile("classpath:invoiceById.jrxml");

        JasperReport jasperReport = JasperCompileManager
                .compileReport(new FileInputStream(file.getAbsolutePath()));

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        byte data[] = JasperExportManager.exportReportToPdf(jasperPrint);

        System.err.println(data);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");


        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);

    }
}
