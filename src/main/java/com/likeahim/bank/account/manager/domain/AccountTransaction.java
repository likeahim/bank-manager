package com.likeahim.bank.account.manager.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "TRANSACTIONS")
public abstract class AccountTransaction {

    @Id
    @GeneratedValue
    private Long id;
}
