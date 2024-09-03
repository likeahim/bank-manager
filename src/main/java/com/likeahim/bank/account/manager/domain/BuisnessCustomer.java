package com.likeahim.bank.account.manager.domain;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "BUISNESS_CUSTOMERS")
public class BuisnessCustomer extends Customer {

    @NonNull
    @Column(name = "COMPANY_NAME")
    private String companyName;
}
