package com.likeahim.bank.account.manager.repository;

import com.likeahim.bank.account.manager.domain.customer.Contact;
import com.likeahim.bank.account.manager.domain.customer.Customer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface ContactRepository extends JpaRepository<Contact, Long> {
    List<Contact> findByCustomer(Customer customer);
}
