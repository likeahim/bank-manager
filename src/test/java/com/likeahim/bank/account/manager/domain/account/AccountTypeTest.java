package com.likeahim.bank.account.manager.domain.account;

import com.likeahim.bank.account.manager.exception.NoSuchAccountTypeException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class AccountTypeTest {

    @Test
    void shouldReturnTypeFromValue() throws NoSuchAccountTypeException {
        //Given
        AccountType type = AccountType.REGULAR;
        //When
        AccountType returnedType = AccountType.fromValue(1);
        //Then
        assertEquals(type, returnedType);
    }

    @Test
    void shouldThrowException() throws NoSuchAccountTypeException {
        //Given
        AccountType type = AccountType.OFFICIAL;
        //When
        //Then
        assertThrows(NoSuchAccountTypeException.class, () -> AccountType.fromValue(9));
    }

    @Test
    void shouldReturnValue() {
        //Given
        //When
        int regular = AccountType.REGULAR.getValue();
        int buisness = AccountType.BUSINESS.getValue();
        int official = AccountType.OFFICIAL.getValue();
        int credit = AccountType.CREDIT.getValue();
        int saving = AccountType.SAVING.getValue();
        //Then
        assertEquals(regular, 1);
        assertEquals(buisness, 2);
        assertEquals(official, 3);
        assertEquals(credit, 4);
        assertEquals(saving, 5);
    }

    @Test
    void shouldReturnName() {
        //Given
        //When
        String regular = AccountType.REGULAR.name();
        String buisness = AccountType.BUSINESS.name();
        String official = AccountType.OFFICIAL.name();
        String credit = AccountType.CREDIT.name();
        String saving = AccountType.SAVING.name();
        //Then
        assertEquals(regular, "REGULAR");
        assertEquals(buisness, "BUSINESS");
        assertEquals(official, "OFFICIAL");
        assertEquals(credit, "CREDIT");
        assertEquals(saving, "SAVING");
    }
}