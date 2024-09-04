package com.likeahim.bank.account.manager.domain.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Shortcut {
    FNC("Tax office"),
    MNC("Municipal office"),
    CNT("County office"),
    VVD("Voivodeoship office"),
    CRT("Court"),
    STT("State office");

    private final String name;

}
