package com.likeahim.bank.account.manager.domain.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CustomerType {
    REGULAR(1),
    BUSINESS(2),
    OFFICIAL(3);
    private final int value;
}
