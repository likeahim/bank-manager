package com.likeahim.bank.account.manager.domain.transaction;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TransactionFee {
    EXTERN_TRANSFER(5),
    INTERN_TRANSFER(0),
    CASH_OUT(0),
    CASH_IN(0),
    BLIK(0.5),
    CREDIT_IN(10);
    private final double fee;
}
