package com.likeahim.bank.account.manager.domain.customer;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "ADDRESSES")
public class Address {

    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    @Column(name = "CITY")
    private String city;

    @NonNull
    @Column(name = "POSTCODE")
    private String postcode;

    @NonNull
    @Column(name = "STREET")
    private String street;

    @Column(name = "RESIDENTIAL_NUMBER")
    private String residentialNumber;

    @Column(name = "COUNTRY")
    private String country; //change to enum with available options

    @Setter(AccessLevel.NONE)
    @ManyToOne
    @JoinColumn(name = "CONTACT_ID")
    private Contact contact;
}
