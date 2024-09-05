package com.likeahim.bank.account.manager.domain.account;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@NoArgsConstructor(force = true)
@SuperBuilder
@Getter
@Setter
@Entity
@Table(name = "CREDIT_ACCOUNTS")
public class CreditAccount extends Account {

    @NonNull
    @Column(name = "CREDIT_AMOUNT")
    private BigDecimal creditAmount;

    @NonNull
    @Column(name = "PERCENTAGE")
    private double percentage;

    @Column(name = "WITHDRAWN_FUNDS")
    private BigDecimal withdrawnFunds;

    @Column(name = "TO_REPAID")
    private BigDecimal toRepaid;
}
