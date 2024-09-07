package com.likeahim.bank.account.manager.strategy.fee;

import com.likeahim.bank.account.manager.domain.account.Account;
import com.likeahim.bank.account.manager.domain.account.AccountFee;

public class CreditFeeStrategy implements FeeStrategy {
    @Override
    public double calculateFee(Account account) {
        return AccountFee.CREDIT.getFee();
    }
}
