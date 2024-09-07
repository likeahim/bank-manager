package com.likeahim.bank.account.manager.domain.account;

import com.likeahim.bank.account.manager.strategy.fee.OfficialFeeStrategy;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor(force = true)
@SuperBuilder
@Getter
@Setter
@Entity
@Table(name = "OFFICIAL_ACCOUNTS")
public class OfficialAccount extends Account {

    @Override
    public void assignFeeStrategy() {
        super.setFeeStrategy(new OfficialFeeStrategy());
    }
}
