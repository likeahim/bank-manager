package com.likeahim.bank.account.manager.domain.customer;

import com.likeahim.bank.account.manager.repository.AddressRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class AddressTests {

    @Autowired
    private AddressRepository addressRepository;

    private Address address;

    @BeforeEach
    void setUp() {
        address = Address.builder()
                .city("Warsaw")
                .country("Poland")
                .postcode("22-100")
                .street("Nowa")
                .residentialNumber("1/1")
                .build();
    }
    @Test
    void shouldCreateAddress() {
        //Given
        //When
        Address saved = addressRepository.save(address);
        //Then
        assertNotNull(saved);
        assertNotNull(saved.getCity());
    }

    @Test
    void shouldUpdateAddress() {
        //Given
        address.setCity("Berlin");
        address.setCountry("Germany");
        address.setPostcode("10-100");
        address.setStreet("Baumannstrasse");
        address.setResidentialNumber("1L");
        //When
        addressRepository.save(address);
        //Then
        Address updatedAddress = addressRepository.findById(address.getId()).get();
        assertEquals("Berlin", updatedAddress.getCity());
        assertEquals("Germany", updatedAddress.getCountry());
        assertEquals(address.getId(), updatedAddress.getId());
    }

    @Test
    void shouldDeleteAddress() {
        //Given
        addressRepository.save(address);
        Long id = address.getId();
        //When
        addressRepository.deleteById(id);
        //Then
        assertTrue(addressRepository.findById(id).isEmpty());
    }
}