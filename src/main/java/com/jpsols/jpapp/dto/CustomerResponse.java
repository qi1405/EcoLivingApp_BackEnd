package com.jpsols.jpapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerResponse {
    private Integer id;
    private String customerNumber;
    private String name;
    private String surname;
    private String area;
    private String city;
    private String address;
    private String phoneNumber;
    private Integer customerTypeID;
    private String enabled;
    private LocalDate dateCreated;
    private Integer pid;
    private BigDecimal credit;
    private BigDecimal debit;
}
