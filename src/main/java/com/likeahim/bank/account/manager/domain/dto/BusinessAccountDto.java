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
@AllArgsConstructor
public class BusinessAccountDto extends AccountDto {
    public BusinessAccountDto(Long id, Long id1, BigDecimal funds, BigDecimal cashOutLimit, @NonNull LocalDate created, @NonNull int accountType, double monthlyFee, List<Long> list) {
    }
}
