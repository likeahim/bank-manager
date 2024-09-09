package com.likeahim.bank.account.manager.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class OfficialAccountDto extends AccountDto {
    public OfficialAccountDto(Long id, Long customerId, BigDecimal funds, BigDecimal cashOutLimit,
                              LocalDate created, int accountType, double monthlyFee, List<Long> transactions) {
        super(id, customerId, funds, cashOutLimit, created, accountType, monthlyFee, transactions);
    }
}
