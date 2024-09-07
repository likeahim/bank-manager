package com.likeahim.bank.account.manager.domain.account;

import com.likeahim.bank.account.manager.strategy.fee.monthly.RegularMonthlyFeeStrategy;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor(force = true)
@SuperBuilder
@Getter
@Setter
@Entity
@Table(name = "REGULAR_ACCOUNTS")
public class RegularAccount extends Account {

    @Transient
    private String blik; //needs some type of automatically generator

    @Override
    public void assignFeeStrategy() {
        super.setMonthlyFeeStrategy(new RegularMonthlyFeeStrategy());
    }
}
