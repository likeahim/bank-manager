package com.likeahim.bank.account.manager.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class SavingAccountDto extends AccountDto {
    private double interest;
    private boolean editable;
    private LocalDate expireDate;

    public SavingAccountDto(Long id, Long customerId, BigDecimal funds, BigDecimal cashOutLimit,
                            LocalDate created, int accountType, double monthlyFee, List<Long> transactions,
                            double interest, boolean editable, LocalDate expireDate) {
        super(id, customerId, funds, cashOutLimit, created, accountType, monthlyFee, transactions);
        this.interest = interest;
        this.editable = editable;
        this.expireDate = expireDate;
    }
}
