package com.likeahim.bank.account.manager.domain.customer;

import com.likeahim.bank.account.manager.domain.account.Account;
import com.likeahim.bank.account.manager.domain.account.AccountType;
import com.likeahim.bank.account.manager.domain.account.OfficialAccount;
import com.likeahim.bank.account.manager.domain.account.RegularAccount;
import com.likeahim.bank.account.manager.repository.AccountRepository;
import com.likeahim.bank.account.manager.repository.ContactRepository;
import com.likeahim.bank.account.manager.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class CustomerTests {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ContactRepository contactRepository;

    private Customer businessCustomer;
    private Customer regularCustomer;
    private Customer officialCustomer;

    @BeforeEach
    void setUp() {
        businessCustomer = BusinessCustomer.builder()
                .type(CustomerType.BUSINESS)
                .companyName("Tester Company")
                .build();
        regularCustomer = RegularCustomer.builder()
                .type(CustomerType.REGULAR)
                .firstName("John")
                .lastName("Smith")
                .pesel("80010101335")
                .idNumber("ASD123456")
                .build();
        officialCustomer = OfficialCustomer.builder()
                .type(CustomerType.OFFICIAL)
                .officialShortcut(Shortcut.STT)
                .build();
    }

    @Test
    void shouldCreateCustomer() {
        //Given
        //When
        customerRepository.save(businessCustomer);
        customerRepository.save(regularCustomer);
        //Then
        assertNotNull(businessCustomer.getId());
        assertEquals(2, customerRepository.findAll().size());
    }

    @Test
    void shouldUpdateCustomer() {
        //Given
        customerRepository.save(officialCustomer);
        officialCustomer.setLogin("maneOffice");
        officialCustomer.setPassword("office1");
        //When
        Customer saved = customerRepository.save(officialCustomer);
        //Then
        assertEquals("maneOffice", saved.getLogin());
        assertEquals("office1", saved.getPassword());
    }

    @Test
    void shouldDeleteCustomer() {
        //Given
        customerRepository.save(businessCustomer);
        customerRepository.save(regularCustomer);
        customerRepository.save(officialCustomer);
        //When
        customerRepository.delete(regularCustomer);
        customerRepository.delete(businessCustomer);
        //Then
        assertEquals(1, customerRepository.findAll().size());
        assertFalse(customerRepository.findAll().contains(regularCustomer));
        assertTrue(customerRepository.findAll().contains(officialCustomer));
    }

    @Test
    void shouldThrowExceptionBySavingInvalidCustomer() {
        //Given
        RegularCustomer customer = new RegularCustomer();
        //When & Then
        assertThrows(DataIntegrityViolationException.class, () -> {
            customerRepository.saveAndFlush(customer);
        });
    }

    @Test
    void shouldCreateEmptyAccountListWithSavingCustomer() {
        //Given
        //When
        customerRepository.save(businessCustomer);
        //Then
        assertEquals(0, businessCustomer.getAccounts().size());
    }

    @Test
    void shouldPersistAccountAndContactWithCustomer() {
        //Given
        RegularAccount account = RegularAccount.builder()
                .customer(regularCustomer)
                .created(LocalDate.now())
                .accountType(AccountType.REGULAR)
                .build();
        Contact contact = Contact.builder()
                .phone("+48604268003")
                .customer(regularCustomer)
                .build();
        regularCustomer.getAccounts().add(account);
        regularCustomer.setContact(contact);
        //When
        customerRepository.save(regularCustomer);
        //Then
        assertNotNull(account.getId());
        assertNotNull(contact.getId());
    }

    @Test
    void shouldDeleteOnlyCustomer() {
        //Given
        Contact contact = new Contact();
        OfficialAccount account = new OfficialAccount();
        officialCustomer.setContact(contact);
        officialCustomer.getAccounts().add(account);
        customerRepository.save(officialCustomer);
        //When
        customerRepository.delete(officialCustomer);
        //Then
        assertTrue(contactRepository.findAll().contains(contact));
        assertTrue(accountRepository.findAll().contains(account));
        assertEquals(0, customerRepository.findAll().size());
    }
}