package com.likeahim.bank.account.manager.domain.account;

import com.likeahim.bank.account.manager.strategy.fee.RegularFeeStrategy;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor(force = true)
@SuperBuilder
@Getter
@Setter
@Entity
@Table(name = "REGULAR_ACCOUNTS")
public class RegularAccount extends Account {

    @Override
    public void assignFeeStrategy() {
        super.setFeeStrategy(new RegularFeeStrategy());
    }
}
