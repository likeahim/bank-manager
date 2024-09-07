package com.likeahim.bank.account.manager.service;

import com.likeahim.bank.account.manager.domain.account.Account;
import com.likeahim.bank.account.manager.domain.account.AccountType;
import com.likeahim.bank.account.manager.domain.customer.Customer;
import com.likeahim.bank.account.manager.exception.AccountDoesNotExistException;
import com.likeahim.bank.account.manager.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account save(final Account account) {
        return accountRepository.save(account);
    }

    public Account findById(final Long id) throws AccountDoesNotExistException {
        return accountRepository.findById(id).orElseThrow(AccountDoesNotExistException::new);
    }

    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    public List<Account> findAllByCustomer(final Customer customer) {
        return accountRepository.findAllByCustomer(customer);
    }

    public List<Account> findAllByAccountType(final AccountType accountType) {
        return accountRepository.findAllByAccountType(accountType);
    }

    public void deleteAccount(final Long id) {
        accountRepository.deleteById(id);
    }
}
