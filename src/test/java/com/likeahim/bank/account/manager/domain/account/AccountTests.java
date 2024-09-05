package com.likeahim.bank.account.manager.domain.account;

import com.likeahim.bank.account.manager.domain.customer.BusinessCustomer;
import com.likeahim.bank.account.manager.domain.customer.RegularCustomer;
import com.likeahim.bank.account.manager.repository.AccountRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class AccountTests {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    void shouldFetchDifferentLists() {
        //Given
        BusinessAccount businessAccount = BusinessAccount.builder()
                .id(null)
                .funds(new BigDecimal(10000))
                .fee(new BigDecimal(10))
                .accountType(AccountType.BUSINESS)
                .created(LocalDate.now())
                .customer(new BusinessCustomer())
                .build();

        RegularAccount regularAccount = RegularAccount.builder()
                .id(null)
                .funds(new BigDecimal(10000))
                .fee(new BigDecimal(10))
                .accountType(AccountType.REGULAR)
                .created(LocalDate.now())
                .customer(new BusinessCustomer())
                .build();
        accountRepository.save(businessAccount);
        accountRepository.save(regularAccount);
        //When
        List<Account> allBusinessAcc = accountRepository.findAllByAccountType(AccountType.BUSINESS);
        List<Account> allRegularAcc = accountRepository.findAllByAccountType(AccountType.REGULAR);
        //Then
        assertEquals(allBusinessAcc.size(), 1);
        assertEquals(allRegularAcc.size(), 1);
    }

    @Test
    void shouldFetchAllAccountsByCustomer() {
        //Given
        RegularCustomer customer = new RegularCustomer();
        CreditAccount creditAccount = new CreditAccount();
        SavingAccount savingAccount = new SavingAccount();
        RegularAccount regularAccount = new RegularAccount();
        savingAccount.setCustomer(customer);
        regularAccount.setCustomer(customer);
        accountRepository.save(creditAccount);
        accountRepository.save(savingAccount);
        accountRepository.save(regularAccount);
        //When
        List<Account> allByCustomer = accountRepository.findAllByCustomer(customer);
        //Then
        assertEquals(allByCustomer.size(), 2);
    }
}