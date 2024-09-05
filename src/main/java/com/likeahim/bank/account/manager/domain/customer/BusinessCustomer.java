package com.likeahim.bank.account.manager.domain.customer;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor(force = true)
@SuperBuilder
@Getter
@Setter
@Entity
@Table(name = "BUISNESS_CUSTOMERS")
public class BusinessCustomer extends Customer {

    @NonNull
    @Column(name = "COMPANY_NAME")
    private String companyName;
}
