package com.likeahim.bank.account.manager.repository;

import com.likeahim.bank.account.manager.domain.account.Account;
import com.likeahim.bank.account.manager.domain.account.AccountType;
import com.likeahim.bank.account.manager.domain.customer.Customer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findAllByAccountType(AccountType accountType);
    List<Account> findAllByCustomer(Customer customer);
}
