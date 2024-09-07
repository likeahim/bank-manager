package com.likeahim.bank.account.manager.domain.account;

import com.likeahim.bank.account.manager.domain.customer.BusinessCustomer;
import com.likeahim.bank.account.manager.domain.customer.RegularCustomer;
import com.likeahim.bank.account.manager.repository.AccountRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class AccountTests {

    @Autowired
    private AccountRepository accountRepository;

    private BusinessAccount businessAccount;
    private RegularAccount regularAccount;

    @BeforeEach
    void setUp() {
        businessAccount = BusinessAccount.builder()
                .id(null)
                .funds(new BigDecimal(10000))
                .accountType(AccountType.BUSINESS)
                .created(LocalDate.now())
                .customer(new BusinessCustomer())
                .build();

        regularAccount = RegularAccount.builder()
                .id(null)
                .funds(new BigDecimal(10000))
                .accountType(AccountType.REGULAR)
                .created(LocalDate.now())
                .customer(new BusinessCustomer())
                .build();
    }
    @Test
    void shouldAssignFeeByAccountType() {
        //Given
        BusinessAccount businessSaved = accountRepository.save(businessAccount);
        RegularAccount regularSaved = accountRepository.save(regularAccount);
        Account business = accountRepository.findById(businessSaved.getId()).get();
        Account regular = accountRepository.findById(regularSaved.getId()).get();
        //When
        double businessFee = business.getFee();
        double regularFee = regular.getFee();
        //Then
        assertEquals(businessFee, 40.0, 0.001);
        assertEquals(regularFee, 10.0, 0.001);
    }

    @Test
    void shouldFetchDifferentLists() {
        //Given
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