package com.likeahim.bank.account.manager.strategy.fee.monthly;

import com.likeahim.bank.account.manager.domain.account.Account;

public interface MonthlyFeeStrategy {
    double assignMonthlyFee(Account account);
}
