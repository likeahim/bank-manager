package com.likeahim.bank.account.manager.domain.transaction;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TransactionType {
    EXTERN_TRANSFER(1),
    INTERN_TRANSFER(2),
    CASH_OUT(3),
    CASH_IN(4),
    BLIK(5),
    CREDIT_IN(6),
    BIG_TRANSFER(7);
    private final int value;
}
