package com.likeahim.bank.account.manager.domain.account;

import com.likeahim.bank.account.manager.domain.customer.Customer;
import com.likeahim.bank.account.manager.domain.transaction.AccountTransaction;
import com.likeahim.bank.account.manager.strategy.fee.FeeStrategy;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "ACCOUNTS")
public abstract class Account {

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
    private AccountType accountType;

    @Column(name = "FEE")
    private double fee;

    @OneToMany(mappedBy = "executor", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<AccountTransaction> transactions;

    @Transient
    private FeeStrategy feeStrategy;

    @PrePersist
    @PreUpdate
    public void prePersistOrUpdate() {
        assignFeeStrategy();
        applyFee();
    }

    public void applyFee() {
        if (feeStrategy != null) {
            this.fee = feeStrategy.calculateFee(this);
        }
    }

    public abstract void assignFeeStrategy();
}
