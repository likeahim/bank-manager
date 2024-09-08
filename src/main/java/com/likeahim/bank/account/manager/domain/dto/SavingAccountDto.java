package com.likeahim.bank.account.manager.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SavingAccountDto extends AccountDto {
    private double interest;
    private boolean editable;
    private LocalDate expireDate;
}
