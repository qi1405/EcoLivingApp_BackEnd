package com.jpsols.jpapp.repository;

import com.jpsols.jpapp.dto.PaymentResponse;
import com.jpsols.jpapp.entity.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
        @Query (value = "SELECT p.invoiceNumber, p.customerNumber, p.paied, p.Id, p.credit\n" +
                "FROM TblCustomer c OUTER APPLY\n" +
                "     (SELECT top 6 p.*\n" +
                "      FROM TblPayments p\n" +
                "      WHERE c.CustomerNumber = p.CustomerNumber\n" +
                "      ORDER BY p.Id DESC \n" +
                "     ) p WHERE c.ID = :id\n" +
                "group by p.invoiceNumber, p.customerNumber, p.paied, p.Id, p.credit", nativeQuery = true)
            List<Payment> findPaymentsByPid (@Param("id") Integer id);

    @Query (value = "SELECT invoiceNumber, customerNumber, monthOfPayment, dateOfPayment, PaymentPeriod, Printed, paied, IsInvoice, Operator, id, debit, credit\n" +
            "                FROM TblPayments\n" +
            "                      WHERE id = :id", nativeQuery = true)
    List<Payment> findInvoiceByPid (@Param("id") Integer id);
}
