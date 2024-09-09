package com.likeahim.bank.account.manager.domain.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.likeahim.bank.account.manager.domain.account.AccountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    private Long id;
    private Long customerId;
    private BigDecimal funds;
    private BigDecimal cashOutLimit;
    private LocalDate created;
    private int accountType;
    private double monthlyFee;
    private List<Long> transactions;
}
