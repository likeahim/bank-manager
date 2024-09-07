package com.likeahim.bank.account.manager.domain.transaction;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "RECIPIENTS")
public class Recipient {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    @Column(name = "IBAN")
    private String accountNumber;

    private String fullName;
    private String fullAddress;
    private String shortName;

    @Builder.Default
    @OneToMany(mappedBy = "recipient", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<AccountTransaction> transactions = new ArrayList<>();
}
