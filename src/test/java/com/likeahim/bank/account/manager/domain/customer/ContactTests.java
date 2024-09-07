package com.likeahim.bank.account.manager.domain.customer;

import com.likeahim.bank.account.manager.repository.AddressRepository;
import com.likeahim.bank.account.manager.repository.ContactRepository;
import com.likeahim.bank.account.manager.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class ContactTests {

    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private CustomerRepository customerRepository;

    private Contact contact;
    @Autowired
    private AddressRepository addressRepository;

    @BeforeEach
    void setUp() {
        Address address = Address.builder()
                .city("Warsaw")
                .postcode("22-100")
                .street("Nowa")
                .build();
        RegularCustomer customer = RegularCustomer.builder()
                .firstName("John")
                .lastName("Smith")
                .idNumber("ASD123456")
                .type(CustomerType.REGULAR)
                .build();
        contact = Contact.builder()
                .phone("+48604268003")
                .email("test@test.pl")
                .addresses(new ArrayList<>(List.of(address)))
                .customer(customer)
                .build();
    }

    @Test
    void shouldCreateContact() {
        //Given
        //When
        Contact saved = contactRepository.save(contact);
        //Then
        assertNotNull(saved.getId());
        assertNotNull(saved.getPhone());
    }

    @Test
    void shouldUpdateContact() {
        //Given
        Address secondAddress = Address.builder()
                .city("Berlin")
                .postcode("11-111")
                .street("Berliner")
                .build();
        contactRepository.save(contact);
        contact.setPhone("+48528268003");
        contact.setEmail("test2@test.pl");
        contact.getAddresses().add(secondAddress);
        //When
        Contact updated = contactRepository.save(contact);
        //Then
        assertNotEquals("+48604268003", updated.getPhone());
        assertEquals("test2@test.pl", contact.getEmail());
        assertEquals(2, contact.getAddresses().size());
    }

    @Test
    void shouldDeleteContact() {
        //Given
        contactRepository.save(contact);
        int sizeBeforeDelete = contactRepository.findAll().size();
        //When
        contactRepository.delete(contact);
        //Then
        assertTrue(contactRepository.findAll().isEmpty());
        assertNotEquals(sizeBeforeDelete, contactRepository.findAll().size());
    }

    @Test
    void shouldSaveAddressWithContact() {
        //Given
        //When
        contactRepository.save(contact);
        //Then
        Long addressId = contact.getAddresses().get(0).getId();
        assertEquals(1, contact.getAddresses().size());
        assertNotNull(contact.getAddresses().get(0).getId());
    }

    @Test
    void shouldDeleteOnlyContact() {
        //Given
        contactRepository.save(contact);
        //When
        contactRepository.delete(contact);
        //Then
        assertFalse(customerRepository.findAll().isEmpty());
        assertFalse(addressRepository.findAll().isEmpty());
    }

    @Test
    void shouldSaveCustomerWithContact() {
        //Given
        //When
        contactRepository.save(contact);
        //Then
        Long customerId = contact.getCustomer().getId();
        assertTrue(customerRepository.findById(customerId).isPresent());
        assertNotNull(contact.getCustomer().getId());
    }
}