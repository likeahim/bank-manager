package com.likeahim.bank.account.manager.domain.account;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AccountMonthlyFee {
    BUSINESS(40.0),
    CREDIT(8.0),
    OFFICIAL(10.0),
    REGULAR(10.0),
    SAVING(0);
    private final double fee;
}
