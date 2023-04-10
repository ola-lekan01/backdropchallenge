package com.backdrop.utils.graphqlresolvers;

import com.backdrop.data.models.AppUser;
import com.backdrop.data.repository.AppUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class AccountQueryResolverTest {
    @Mock
    private AppUserRepository userRepositoryMock;
    @InjectMocks
    private AccountQueryResolver resolverMock;
    private final String ACCOUNT_NUMBER = "0108510992";
    private final String BANK_CODE = "058";

    @Test
    public void testGetAccountNameSuccess() {
        // Setting up variables
        AppUser appUser = new AppUser();
        appUser.setAccountNumber(ACCOUNT_NUMBER);
        appUser.setBankCode(BANK_CODE);

        // Setting up repository mocks
        when(userRepositoryMock.findByAccountNumberAndBankCode(ACCOUNT_NUMBER, BANK_CODE)).thenReturn(Optional.of(appUser));

        // Making a Method call
        AppUser result = resolverMock.getAccountName(ACCOUNT_NUMBER, BANK_CODE);

        // verify repository method calls
        verify(userRepositoryMock, times(1)).findByAccountNumberAndBankCode(ACCOUNT_NUMBER, BANK_CODE);

        // verifying response are as expected
        assertEquals(result, appUser);
    }

    @Test
    public void testGetAccountNameNotFound() {
        //Setting up mocks
        when(userRepositoryMock.findByAccountNumberAndBankCode(ACCOUNT_NUMBER, BANK_CODE)).thenReturn(Optional.empty());

        //Making method calls
        AppUser result = resolverMock.getAccountName(ACCOUNT_NUMBER, BANK_CODE);

        // verify repository method calls
        verify(userRepositoryMock, times(1)).findByAccountNumberAndBankCode(ACCOUNT_NUMBER, BANK_CODE);

        // verifying response are as expected
        assertNull(result);
    }
}