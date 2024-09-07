package com.likeahim.bank.account.manager.domain.transaction;

import com.likeahim.bank.account.manager.domain.account.Account;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
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

    @NonNull
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "EXECUTOR")
    private Account executor;

    @NonNull
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "RECEIVER_ID")
    private Recipient recipient;
}
