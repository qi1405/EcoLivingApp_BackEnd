package com.jpsols.jpapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "tblCustomer")
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "CustomerNumber", length = 6)
    private String customerNumber;

    @Column(name = "Name", length = 50)
    private String name;

    @Column(name = "Surname", length = 50)
    private String surname;

    @Column(name = "Area", length = 50)
    private String area;

    @Column(name = "City", length = 50)
    private String city;

    @Column(name = "Address", length = 150)
    private String address;

    @Column(name = "PhoneNumber", length = 50)
    private String phoneNumber;

    @Column(name = "CustomerTypeID")
    private Integer customerTypeID;

    @Column(name = "Enabled", length = 1)
    private String enabled;

    @Column(name = "DateCreated")
    private LocalDate dateCreated;

    @OneToMany(targetEntity = Payment.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "CustomerNumber", referencedColumnName = "CustomerNumber")
    private List<Payment> payments;
}
