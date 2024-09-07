package com.likeahim.bank.account.manager.domain.account;

import com.likeahim.bank.account.manager.domain.customer.BusinessCustomer;
import com.likeahim.bank.account.manager.domain.customer.CustomerType;
import com.likeahim.bank.account.manager.domain.customer.RegularCustomer;
import com.likeahim.bank.account.manager.repository.AccountRepository;
import com.likeahim.bank.account.manager.repository.CustomerRepository;
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
    @Autowired
    private CustomerRepository customerRepository;

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
                .blik("123456")
                .customer(new BusinessCustomer())
                .build();
    }

    @Test
    void shouldCreateAccount() {
        //Given
        //When
        RegularAccount regularSaved = accountRepository.save(regularAccount);
        //Then
        Long id = regularAccount.getId();
        assertNotNull(id);
        assertEquals("123456", regularSaved.getBlik());
    }

    @Test
    void shouldUpdateAccount() {
        //Given
        accountRepository.save(businessAccount);
        businessAccount.setCashOutLimit(new BigDecimal(5000));
        businessAccount.setMonthlyFee(20.0);
        //When
        accountRepository.save(businessAccount);
        //Then
        assertNotNull(businessAccount.getId());
        assertEquals(5000, businessAccount.getCashOutLimit().intValue());
        assertEquals(20.0, businessAccount.getMonthlyFee());
    }

    @Test
    void shouldDeleteAccount() {
        //Given
        accountRepository.save(businessAccount);
        Long id = businessAccount.getId();
        //When
        accountRepository.deleteById(id);
        //Then
        Optional<Account> byId = accountRepository.findById(id);
        assertTrue(byId.isEmpty());
    }

    @Test
    void shouldSaveCustomerWithAccount() {
        //Given
        CreditAccount creditAccount = CreditAccount.builder()
                .customer(new RegularCustomer())
                .created(LocalDate.now())
                .accountType(AccountType.CREDIT)
                .creditAmount(new BigDecimal(30000))
                .percentage(2.5)
                .withdrawnFunds(BigDecimal.ZERO)
                .toRepaid(new BigDecimal(30000))
                .build();
        //When
        accountRepository.save(creditAccount);
        //Then
        assertEquals(2.5, creditAccount.getPercentage());
        assertEquals(30000, creditAccount.getCreditAmount().intValue());
        assertEquals(BigDecimal.ZERO, creditAccount.getWithdrawnFunds());
        assertEquals(LocalDate.now(), creditAccount.getCreated());
        assertNotNull(creditAccount.getId());
        assertNotNull(creditAccount.getCustomer().getId());
    }

    @Test
    void shouldAssignMonthlyFeeByAccountType() {
        //Given
        BusinessAccount businessSaved = accountRepository.save(businessAccount);
        RegularAccount regularSaved = accountRepository.save(regularAccount);
        Account business = accountRepository.findById(businessSaved.getId()).get();
        Account regular = accountRepository.findById(regularSaved.getId()).get();
        //When
        double businessFee = business.getMonthlyFee();
        double regularFee = regular.getMonthlyFee();
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
    void shouldFetchEmptyListByAccountType() {
        //Given
        //When
        List<Account> allByAccountType = accountRepository.findAllByAccountType(AccountType.BUSINESS);
        //Then
        assertEquals(allByAccountType.size(), 0);
    }

    @Test
    void shouldFetchAllAccountsByCustomer() {
        //Given
        RegularCustomer customer = RegularCustomer.builder()
                .type(CustomerType.REGULAR)
                .firstName("John")
                .lastName("Smith")
                .idNumber("ASD123456")
                .build();
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

    @Test
    void shouldFetchEmptyListByCustomer() {
        //Given
        RegularCustomer customer = RegularCustomer.builder()
                .type(CustomerType.REGULAR)
                .firstName("John")
                .lastName("Smith")
                .idNumber("ASD123456")
                .build();
        customerRepository.save(customer);
        accountRepository.save(regularAccount);
        //When
        List<Account> allByCustomer = accountRepository.findAllByCustomer(customer);
        //Then
        assertEquals(allByCustomer.size(), 0);
    }
}