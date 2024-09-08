package com.likeahim.bank.account.manager.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
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

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "accountType"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = BusinessAccountDto.class, name = "2"),
        @JsonSubTypes.Type(value = CreditAccountDto.class, name = "4"),
        @JsonSubTypes.Type(value = OfficialAccountDto.class, name = "3"),
        @JsonSubTypes.Type(value = RegularAccountDto.class, name = "1"),
        @JsonSubTypes.Type(value = SavingAccountDto.class, name = "5")
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class AccountDto {
    private Long id;
    private Long customerId;
    private BigDecimal funds;
    private BigDecimal cashOutLimit;
    private LocalDate created;
    private int accountType;
    private double monthlyFee;
    private List<Long> transactions;
}
