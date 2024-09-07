package com.likeahim.bank.account.manager.strategy.fee;

import com.likeahim.bank.account.manager.domain.account.Account;
import com.likeahim.bank.account.manager.domain.account.AccountFee;

public interface FeeStrategy {
    double calculateFee(Account account);
}
