package com.likeahim.bank.account.manager.domain.transaction;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MoneyDirection {
    IN(1),
    OUT(2);
    private final int value;
}
