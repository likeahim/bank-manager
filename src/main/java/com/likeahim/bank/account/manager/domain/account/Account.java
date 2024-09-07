package com.likeahim.bank.account.manager.domain.account;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.likeahim.bank.account.manager.domain.customer.Customer;
import com.likeahim.bank.account.manager.domain.transaction.AccountTransaction;
import com.likeahim.bank.account.manager.strategy.fee.monthly.MonthlyFeeStrategy;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SuperBuilder
@NoArgsConstructor
@Getter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "ACCOUNTS")
public abstract class Account {

    @Id
    @GeneratedValue
    private Long id;

//    @NonNull
    @Setter
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;

    @Column(name = "FUNDS")
    private BigDecimal funds;

    @Setter
    @Column(name = "CASH_OUT_LIMIT")
    private BigDecimal cashOutLimit;

    @NonNull
    @Column(name = "CREATED")
    private LocalDate created;

    @NonNull
    @Column(name = "TYPE", updatable = false)
    private AccountType accountType;

    @Setter
    @Column(name = "MONTHLY_FEE")
    private double monthlyFee;

    @OneToMany(mappedBy = "executor", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<AccountTransaction> transactions;

    @Transient
    @Setter
    private MonthlyFeeStrategy monthlyFeeStrategy;

    @PrePersist
    @PreUpdate
    public void prePersistOrUpdate() {
        assignFeeStrategy();
        applyFee();
    }

    public void applyFee() {
        if (monthlyFeeStrategy != null) {
            this.monthlyFee = monthlyFeeStrategy.assignMonthlyFee(this);
        }
    }

    public abstract void assignFeeStrategy();
}
