package com.likeahim.bank.account.manager.domain.account;

import com.likeahim.bank.account.manager.strategy.fee.SavingFeeStrategy;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@NoArgsConstructor(force = true)
@SuperBuilder
@Getter
@Setter
@Entity
@Table(name = "SAVING_ACCOUNTS")
public class SavingAccount extends Account {

    @NonNull
    @Column(name = "INTEREST")
    private double interest;

    @Column(name = "EDITABLE")
    private boolean editable;

    @NonNull
    @Column(name = "EXPIRE_DATE")
    private LocalDate expireDate;

    @Override
    public void assignFeeStrategy() {
        super.setFeeStrategy(new SavingFeeStrategy());
    }
}
