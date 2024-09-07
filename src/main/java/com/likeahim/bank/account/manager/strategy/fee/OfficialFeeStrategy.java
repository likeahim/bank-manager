package com.likeahim.bank.account.manager.strategy.fee;

import com.likeahim.bank.account.manager.domain.account.Account;
import com.likeahim.bank.account.manager.domain.account.AccountFee;

public class OfficialFeeStrategy implements FeeStrategy {
    @Override
    public double calculateFee(Account account) {
        return AccountFee.OFFICIAL.getFee();
    }
}
