package com.likeahim.bank.account.manager.domain.account;

import com.likeahim.bank.account.manager.strategy.fee.monthly.BusinessMonthlyFeeStrategy;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor(force = true)
@SuperBuilder
@Getter
@Setter
@Entity
@Table(name = "BUISNESS_ACCOUNTS")
public class BusinessAccount extends Account {

    @Override
    public void assignFeeStrategy() {
        super.setMonthlyFeeStrategy(new BusinessMonthlyFeeStrategy());
    }
}
