package com.likeahim.bank.account.manager.domain.dto;

import com.likeahim.bank.account.manager.domain.account.AccountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class BusinessAccountDto extends AccountDto {
    public BusinessAccountDto(Long id, Long customerId, BigDecimal funds, BigDecimal cashOutLimit,
                              LocalDate created, int accountType, double monthlyFee, List<Long> transactions) {
        super(id, customerId, funds, cashOutLimit, created, accountType, monthlyFee, transactions);
    }
}
