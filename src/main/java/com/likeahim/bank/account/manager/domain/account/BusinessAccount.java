package com.likeahim.bank.account.manager.domain.account;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "BUISNESS_ACCOUNTS")
public class BusinessAccount extends Account {
}
