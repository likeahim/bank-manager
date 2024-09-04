package com.likeahim.bank.account.manager.domain.account;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "OFFICIAL_ACCOUNTS")
public class OfficialAccount extends Account {
}
