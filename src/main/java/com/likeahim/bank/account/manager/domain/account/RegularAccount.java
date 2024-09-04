package com.likeahim.bank.account.manager.domain.account;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "REGULAR_ACCOUNTS")
public class RegularAccount extends Account {
}
