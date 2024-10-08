package com.likeahim.bank.account.manager.domain.customer;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "CONTACTS")
public class    Contact {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    @Column(name = "PHONE")
    private String phone;

    @Column(name = "EMAIL")
    private String email;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;

    @Builder.Default
    @OneToMany(mappedBy = "contact" , cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Address> addresses = new ArrayList<>();
}
