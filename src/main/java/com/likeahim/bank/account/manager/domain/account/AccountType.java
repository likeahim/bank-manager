package com.likeahim.bank.account.manager.domain.account;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.likeahim.bank.account.manager.exception.NoSuchAccountTypeException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AccountType {
    REGULAR(1),
    BUSINESS(2),
    OFFICIAL(3),
    CREDIT(4),
    SAVING(5);
    private final int value;

    public static AccountType fromValue(int value) throws NoSuchAccountTypeException {
        return switch (value) {
            case 1 -> REGULAR;
            case 2 -> BUSINESS;
            case 3 -> OFFICIAL;
            case 4 -> CREDIT;
            case 5 -> SAVING;
            default -> throw new NoSuchAccountTypeException();
        };
    }
}
