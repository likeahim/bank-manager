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
@Table(name = "ACCOUNTS")
public abstract class Account {

    @Id
    @GeneratedValue
    private Long id;
}
