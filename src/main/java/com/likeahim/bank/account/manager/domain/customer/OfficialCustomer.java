package com.likeahim.bank.account.manager.domain.customer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "OFFICIAL_CUSTOMERS")
public class OfficialCustomer extends Customer {

    @NonNull
    @Column(name = "SHORTCUT")
    private Shortcut officialShortcut;
}
