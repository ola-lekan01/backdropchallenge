package com.backdrop.service.impl;

import com.backdrop.data.dtos.Account.Data;
import com.backdrop.data.models.AppUser;
import com.backdrop.service.GraphQLService;
import com.backdrop.service.PayStackService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userServiceMock;
    @Mock
    private PayStackService payStackServiceMock;
    @Mock
    private GraphQLService graphQLServiceMock;
    private final String ACCOUNT_NAME = "Sofuyi Olalekan Samson";
    private final String ACCOUNT_NUMBER = "0108510992";
    private final String BANK_CODE = "058";
    private AppUser expectedAppUser;


    @BeforeEach
    void setUp() {
        expectedAppUser = new AppUser();
        expectedAppUser.setAccountNumber(ACCOUNT_NUMBER);
        expectedAppUser.setBankCode(BANK_CODE);
        expectedAppUser.setAccountName(ACCOUNT_NAME);
    }

    @Test
    void addUserAccountWithStatusVerifiedWhenTheInputNameMatchesWithTheApiName() throws IOException {
        //Setting up Variables
        Data accountData = new Data();
        accountData.setAccount_name(ACCOUNT_NAME);
        expectedAppUser.setIsVerified(true);

        // Setting up Repository Mocks
        when(payStackServiceMock.getAccountName(eq(ACCOUNT_NUMBER), eq(BANK_CODE))).thenReturn(accountData);
        when(graphQLServiceMock.addAccountUser(eq(ACCOUNT_NAME.toLowerCase()), eq(ACCOUNT_NUMBER), eq(BANK_CODE), eq(true)))
                .thenReturn(expectedAppUser);

        // Act
        AppUser result = userServiceMock.addUserAccount(ACCOUNT_NAME, ACCOUNT_NUMBER, BANK_CODE);

        // Assert
        assertNotNull(result);
        assertEquals(ACCOUNT_NAME, result.getAccountName());
        assertTrue(result.getIsVerified());

        //Verifying Method Calls
        verify(payStackServiceMock, times(1)).getAccountName(eq(ACCOUNT_NUMBER), eq(BANK_CODE));
        verify(graphQLServiceMock, times(1)).addAccountUser(eq(ACCOUNT_NAME.toLowerCase()), eq(ACCOUNT_NUMBER), eq(BANK_CODE), eq(true));
    }

    @Test
    void addUserAccountWithStatusNotVerifiedWhenTheInputNameDifferWithMoreThanTwoWithTheApiName() throws IOException {
        // Setting up variables
        Data accountData = new Data();
        accountData.setAccount_name("lekan samson");
        expectedAppUser.setAccountName(accountData.getAccount_name().toLowerCase());
        expectedAppUser.setIsVerified(false);

        // Setting up method calls
        when(payStackServiceMock.getAccountName(eq(ACCOUNT_NUMBER), eq(BANK_CODE))).thenReturn(accountData);
        when(graphQLServiceMock.addAccountUser(eq(accountData.getAccount_name()), eq(ACCOUNT_NUMBER), eq(BANK_CODE), eq(false)))
                .thenReturn(expectedAppUser);

        // Act
        AppUser result = userServiceMock.addUserAccount(ACCOUNT_NAME, ACCOUNT_NUMBER, BANK_CODE);

        // Assert
        assertNotNull(result);
        assertEquals(accountData.getAccount_name(), result.getAccountName());
        assertFalse(result.getIsVerified());

        //Verifying Method Calls
        verify(payStackServiceMock, times(1)).getAccountName(eq(ACCOUNT_NUMBER), eq(BANK_CODE));
        verify(graphQLServiceMock, times(1)).addAccountUser(eq(accountData.getAccount_name()), eq(ACCOUNT_NUMBER), eq(BANK_CODE), eq(false));
    }

    @Test
    void getAccountNameForExistingUser() throws IOException {
        // Setting up method calls
        when(graphQLServiceMock.getAccountName(eq(ACCOUNT_NUMBER), eq(BANK_CODE))).thenReturn(expectedAppUser);

        // Act
        AppUser result = userServiceMock.getAccountName(ACCOUNT_NUMBER, BANK_CODE);

        // Assert
        assertNotNull(result);
        assertEquals(ACCOUNT_NAME, result.getAccountName());

        //Verifying Method Calls
        verify(graphQLServiceMock, times(1)).getAccountName(eq(ACCOUNT_NUMBER), eq(BANK_CODE));
    }
}