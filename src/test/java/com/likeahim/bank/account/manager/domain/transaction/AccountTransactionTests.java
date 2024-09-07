package com.likeahim.bank.account.manager.domain.transaction;

import com.likeahim.bank.account.manager.domain.account.Account;
import com.likeahim.bank.account.manager.domain.account.AccountType;
import com.likeahim.bank.account.manager.domain.account.OfficialAccount;
import com.likeahim.bank.account.manager.domain.customer.Customer;
import com.likeahim.bank.account.manager.domain.customer.CustomerType;
import com.likeahim.bank.account.manager.domain.customer.OfficialCustomer;
import com.likeahim.bank.account.manager.domain.customer.Shortcut;
import com.likeahim.bank.account.manager.repository.AccountRepository;
import com.likeahim.bank.account.manager.repository.RecipientRepository;
import com.likeahim.bank.account.manager.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class AccountTransactionTests {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private RecipientRepository recipientRepository;

    private Account executor;
    private Recipient recipient;
    private AccountTransaction transaction;

    @BeforeEach
    void setUp() {
        Customer customer = OfficialCustomer.builder()
                .type(CustomerType.OFFICIAL)
                .officialShortcut(Shortcut.CNT)
                .build();
        executor = OfficialAccount.builder()
                .customer(customer)
                .created(LocalDate.now())
                .accountType(AccountType.OFFICIAL)
                .build();
        recipient = Recipient.builder()
                .accountNumber("PL23100000001234567812345678")
                .build();
        transaction = AccountTransaction.builder()
                .direction(MoneyDirection.OUT)
                .type(TransactionType.CASH_OUT)
                .executor(executor)
                .recipient(recipient)
                .build();
    }

    @Test
    void shouldCreateTransaction() {
        //Given
        //When
        transactionRepository.save(transaction);
        //Then
        Long id = transaction.getId();
        assertNotNull(id);
        assertFalse(transactionRepository.findAll().isEmpty());
    }

    @Test
    void shouldFetchTransactionById() {
        //Given
        AccountTransaction save = transactionRepository.save(transaction);
        //When
        Optional<AccountTransaction> byId = transactionRepository.findById(save.getId());
        //Then
        assertTrue(byId.isPresent());
    }

    @Test
    void shouldUpdateTransaction() {
        //Given
        transactionRepository.save(transaction);
        transaction.setTransactionFee(20);
        transaction.setDirection(MoneyDirection.IN);
        transaction.setType(TransactionType.BLIK);
        //When
        transactionRepository.save(transaction);
        //Then
        AccountTransaction accountTransaction = transactionRepository.findById(transaction.getId()).get();
        assertEquals(20, accountTransaction.getTransactionFee());
        assertEquals(MoneyDirection.IN, accountTransaction.getDirection());
        assertEquals(TransactionType.BLIK, accountTransaction.getType());
    }

    @Test
    void shouldDeleteTransaction() {
        //Given
        transactionRepository.save(transaction);
        Long id = transaction.getId();
        //When
        transactionRepository.deleteById(id);
        //Then
        assertTrue(transactionRepository.findById(id).isEmpty());
        assertTrue(transactionRepository.findAll().isEmpty());
    }

    @Test
    void shouldAssignTransferFeeBySavingTransaction() {
        //Given
        double beforeChange = transaction.getTransactionFee();
        transaction.setType(TransactionType.EXTERN_TRANSFER);
        //When
        transactionRepository.save(transaction);
        //Then
        assertEquals(5.0, transaction.getTransactionFee());
        assertEquals(0, beforeChange);
        assertNotEquals(beforeChange, transaction.getTransactionFee());
    }

    @Test
    void shouldSaveExecutorWithTransaction() {
        //Given
        //When
        transactionRepository.save(transaction);
        //Then
        assertFalse(transactionRepository.findAll().isEmpty());
        assertNotNull(transaction.getExecutor().getId());
    }

    @Test
    void shouldSaveRecipientWithTransaction() {
        //Given
        //When
        transactionRepository.save(transaction);
        //Then
        assertFalse(transactionRepository.findAll().isEmpty());
        assertNotNull(transaction.getRecipient().getId());
    }

    @Test
    void shouldDeleteOnlyTransaction() {
        //Given
        transactionRepository.save(transaction);
        //When
        transactionRepository.delete(transaction);
        //Then
        assertTrue(transactionRepository.findAll().isEmpty());
        assertTrue(accountRepository.findAll().contains(executor));
        assertTrue(recipientRepository.findAll().contains(recipient));
    }
}