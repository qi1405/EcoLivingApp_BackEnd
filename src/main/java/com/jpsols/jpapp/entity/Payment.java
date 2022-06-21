package com.jpsols.jpapp.entity;

//import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table
@Entity
public class Payment {
    @Id
    @Column(name = "Id", nullable = false)
    private Integer pid;

    @Column(name = "CustomerNumber", length = 6)
    private String customerNumber;

    @Column(name = "InvoiceNumber", length = 13)
    private String invoiceNumber;

    @Column(name = "MonthOfPayment", length = 7)
    private String monthOfPayment;

    @Column(name = "DateOfPayment")
    private Instant dateOfPayment;

    @Column(name = "Credit", precision = 18, scale = 2)
    private BigDecimal credit;

    @Column(name = "Debit", precision = 18, scale = 2)
    private BigDecimal debit;

    @Column(name = "PaymentPeriod", length = 1)
    private String paymentPeriod;

    @Column(name = "Operator")
    private Integer operator;

    @Column(name = "Printed", length = 1)
    private String printed;

    @Column(name = "Paied", length = 1)
    private String paid;

    @Column(name = "IsInvoice", length = 1)
    private String isInvoice;
}
