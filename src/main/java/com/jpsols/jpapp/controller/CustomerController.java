package com.jpsols.jpapp.controller;
import com.jpsols.jpapp.dto.CustomerResponse;
import com.jpsols.jpapp.dto.PaymentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/data")
public class CustomerController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/customers")
    @PreAuthorize("hasRole('USER')")
    public List<CustomerResponse> getAllCustomers() {
        String sql = "select top 20 c.ID, c.CustomerNumber, c.Name, c.Surname, c.Area, c.City, c.Address, c.PhoneNumber, c.CustomerTypeID, c.Enabled, c.DateCreated, sum(p.Debit)as Debit, sum(p.Credit) as Credit\n" +
                "from tblCustomer c\n" +
                "left join tblPayments p on c.CustomerNumber = p.CustomerNumber\n" +
                "group by c.ID, c.CustomerNumber, c.Name, c.Surname, c.Area, c.City, c.Address, c.PhoneNumber, c.CustomerTypeID, c.Enabled, c.DateCreated;"
                ;
        List<CustomerResponse> result = jdbcTemplate.query(sql, new BeanPropertyRowMapper(CustomerResponse.class));

        return result;
    }

    @GetMapping("/customers/{id}")
    @PreAuthorize("hasRole('USER')")
    public List<CustomerResponse> getCustomerByID(@PathVariable Integer id) {
        String sql = "select c.ID, c.CustomerNumber, c.Name, c.Surname, c.Area, c.City, c.Address, c.PhoneNumber, c.CustomerTypeID, c.Enabled, c.DateCreated, sum(p.Debit)as Debit, sum(p.Credit) as Credit\n" +
                "from tblCustomer c\n" +
                "left join tblPayments p on c.CustomerNumber = p.CustomerNumber\n" +
                "where c.ID = " + id + "\n" +
                "group by c.ID, c.CustomerNumber, c.Name, c.Surname, c.Area, c.City, c.Address, c.PhoneNumber, c.CustomerTypeID, c.Enabled, c.DateCreated;"
                ;
        List<CustomerResponse> result = jdbcTemplate.query(sql, new BeanPropertyRowMapper(CustomerResponse.class));

        return result;
    }

    @GetMapping("/customers/{id}/invoices")
    @PreAuthorize("hasRole('USER')")
    public List<PaymentResponse> getInvoicesByCustomerID(@PathVariable Integer id) {
        String sql = "SELECT p.InvoiceNumber, p.MonthOfPayment, p.DateOfPayment, p.PaymentPeriod, p.Printed, p.Paied, p.IsInvoice, p.Operator, p.Id as pid, p.Debit, p.Credit\n" +
                "FROM TblCustomer c OUTER APPLY\n" +
                "     (SELECT TOP 6 p.*\n" +
                "      FROM TblPayments p\n" +
                "      WHERE c.CustomerNumber = p.CustomerNumber\n" +
                "      ORDER BY p.id DESC \n" +
                "     ) p WHERE c.ID = " + id + ""
                ;
        List<PaymentResponse> result = jdbcTemplate.query(sql, new BeanPropertyRowMapper(PaymentResponse.class));

        return result;
    }

    @GetMapping("/customers/{id}/allinvoices")
    @PreAuthorize("hasRole('USER')")
    public List<PaymentResponse> getAllInvoicesByCustomerID(@PathVariable Integer id) {
        String sql = "SELECT p.InvoiceNumber, p.MonthOfPayment, p.DateOfPayment, p.PaymentPeriod, p.Printed, p.Paied, p.IsInvoice, p.Operator, p.Id as pid, p.Debit, p.Credit\n" +
                "FROM TblCustomer c OUTER APPLY\n" +
                "     (SELECT top 100 p.*\n" +
                "      FROM TblPayments p\n" +
                "      WHERE c.CustomerNumber = p.CustomerNumber\n" +
                "      ORDER BY p.id DESC \n" +
                "     ) p WHERE c.ID = " + id + ""
                ;
        List<PaymentResponse> result = jdbcTemplate.query(sql, new BeanPropertyRowMapper(PaymentResponse.class));

        return result;
    }

    @GetMapping("/customers/invoices/{id}")
    @PreAuthorize("hasRole('USER')")
    public List<PaymentResponse> getInvoiceByID(@PathVariable Integer id) {
        String sql = "SELECT InvoiceNumber, MonthOfPayment, DateOfPayment, PaymentPeriod, Printed, Paied, IsInvoice, Operator, Id as pid, Debit, Credit\n" +
                "                FROM TblPayments\n" +
                "                      WHERE ID =" + id + ""
                ;
        List<PaymentResponse> result = jdbcTemplate.query(sql, new BeanPropertyRowMapper(PaymentResponse.class));

        return result;
    }

    @GetMapping("/customer/{id}")
    @PreAuthorize("hasRole('USER')")
    public List<CustomerResponse> getSingleCustomerByID(@PathVariable Integer id) {
        String sql = "SELECT Id, CustomerNumber, Name, Surname, Area, City, Address, PhoneNumber, CustomerTypeID, Enabled, DateCreated\n" +
                "                FROM TblCustomer\n" +
                "                      WHERE Id =" + id + ""
                ;
        List<CustomerResponse> result = jdbcTemplate.query(sql, new BeanPropertyRowMapper(CustomerResponse.class));

        return result;
    }
}