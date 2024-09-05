package com.likeahim.bank.account.manager.domain.account;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor(force = true)
@SuperBuilder
@Getter
@Setter
@Entity
@Table(name = "OFFICIAL_ACCOUNTS")
public class OfficialAccount extends Account {
}
