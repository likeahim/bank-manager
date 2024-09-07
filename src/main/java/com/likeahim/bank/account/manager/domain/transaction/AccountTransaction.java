package com.likeahim.bank.account.manager.domain.transaction;

import com.likeahim.bank.account.manager.domain.account.Account;
import com.likeahim.bank.account.manager.exception.UnknownTransactionType;
import com.likeahim.bank.account.manager.strategy.fee.transaction.TransactionFeeStrategy;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "TRANSACTIONS")
public class AccountTransaction {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    @Column(name = "DIRECTION")
    private MoneyDirection direction;

    @NonNull
    @Column(name = "TYPE")
    private TransactionType type;

    @Column(name = "TRANSACTION_FEE")
    private double transactionFee;

    @NonNull
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "EXECUTOR")
    private Account executor;

    @NonNull
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "RECEIVER_ID")
    private Recipient recipient;

    @PrePersist
    @PreUpdate
    private void prePersist() throws UnknownTransactionType {
        TransactionFeeStrategy.assignTransferFee(this);
    }
}
