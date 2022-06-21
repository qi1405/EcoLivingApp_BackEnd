package com.jpsols.jpapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaymentResponse {
    private Integer pid;
    private BigDecimal credit;
    private BigDecimal debit;
    private String invoiceNumber;
    private String monthOfPayment;
    private LocalDate dateOfPayment;
    private String paymentPeriod;
    private Integer operator;
    private String paied;
    private String printed;
    private String isInvoice;
}
