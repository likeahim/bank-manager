package com.likeahim.bank.account.manager.domain.customer;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor(force = true)
@SuperBuilder
@Getter
@Setter
@Entity
@Table(name = "REGULAR_CUSTOMERS")
public class RegularCustomer extends Customer {

    @Column(name = "FIRSTNAME", nullable = false)
    private String firstName;

    @Column(name = "LASTNAME", nullable = false)
    private String lastName;

    @Column(name = "PESEL", unique = true)
    private String pesel;

    @Column(name = "ID_NUMBER", unique = true, nullable = false)
    private String idNumber;
}
