package com.likeahim.bank.account.manager.domain.dto;

import com.likeahim.bank.account.manager.domain.account.AccountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CreditAccountDto extends AccountDto {
    private BigDecimal creditAmount;
    private double percentage;
    private BigDecimal withdrawnFunds;
    private BigDecimal toRepaid;

    public CreditAccountDto(
            Long id, Long customerId, BigDecimal founds, BigDecimal cashOutLimit,
            LocalDate created, int accountType, double monthlyFee,
            List<Long> transactions, BigDecimal creditAmount, double percentage,
            BigDecimal withdrawnFunds, BigDecimal toRepaid) {
        super(id, customerId, founds, cashOutLimit, created, accountType, monthlyFee, transactions);
        this.creditAmount = creditAmount;
        this.percentage = percentage;
        this.withdrawnFunds = withdrawnFunds;
        this.toRepaid = toRepaid;
    }
}
