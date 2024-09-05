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

    @NonNull
    @Column(name = "FIRSTNAME")
    private String firstName;

    @NonNull
    @Column(name = "LASTNAME")
    private String lastName;

    @Column(name = "PESEL", unique = true)
    private String pesel;

    @NonNull
    @Column(name = "ID_NUMBER", unique = true)
    private String idNumber;
}
