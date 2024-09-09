package com.likeahim.bank.account.manager.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class RegularAccountDto extends AccountDto {
    private String blik;

    public RegularAccountDto(Long id, Long customerId, BigDecimal funds, BigDecimal cashOutLimit,
                             LocalDate created, int accountType, double monthlyFee, List<Long> transactions,
                             String blik) {
        super(id, customerId, funds, cashOutLimit, created, accountType, monthlyFee, transactions);
        this.blik = blik;
    }
}
