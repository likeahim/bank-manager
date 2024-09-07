package com.likeahim.bank.account.manager.strategy.fee.monthly;

import com.likeahim.bank.account.manager.domain.account.Account;
import com.likeahim.bank.account.manager.domain.account.AccountMonthlyFee;

public class OfficialMonthlyFeeStrategy implements MonthlyFeeStrategy {
    @Override
    public double assignMonthlyFee(Account account) {
        return AccountMonthlyFee.OFFICIAL.getFee();
    }
}
