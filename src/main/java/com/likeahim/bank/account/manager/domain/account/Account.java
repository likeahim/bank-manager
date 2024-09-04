package com.likeahim.bank.account.manager.domain.account;

import com.likeahim.bank.account.manager.domain.customer.Customer;
import com.likeahim.bank.account.manager.domain.transaction.AccountTransaction;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "ACCOUNTS")
public abstract class Account {

  //#########################
//AllArgsConstructor - where?


    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;

    @Column(name = "FUNDS")
    private BigDecimal funds;

    @Column(name = "CASH_OUT_LIMIT")
    private BigDecimal cashOutLimit;

    @NonNull
    @Column(name = "CREATED")
    private LocalDate created;

    @NonNull
    @Column(name = "TYPE", updatable = false)
    private AccountType type;

    @Column(name = "FEE")
    private BigDecimal fee;

    @OneToMany(mappedBy = "executor", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<AccountTransaction> transactions;
}
