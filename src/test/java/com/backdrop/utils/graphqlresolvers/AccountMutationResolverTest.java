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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class AccountMutationResolverTest {
    @Mock
    private AppUserRepository userRepository;
    @InjectMocks
    private AccountMutationResolver accountMutationResolver;

    @Test
    public void testAddAccountUser() {
        //Setting up variables
        String accountName = "Sofuyi Olalekan Samson";
        String accountNumber = "0108510992";
        String bankCode = "058";
        Boolean isVerified = true;

        AppUser expectedUser = AppUser.builder()
                .accountName(accountName)
                .accountNumber(accountNumber)
                .bankCode(bankCode)
                .isVerified(isVerified)
                .build();

        //Setting up repository Mocks
        when(userRepository.save(any(AppUser.class))).thenReturn(expectedUser);

        AppUser actualUser = accountMutationResolver.addAccountUser(
                accountName,
                accountNumber,
                bankCode,
                isVerified
        );

        //Verifying Method Calls
        verify(userRepository, times(1)).save(any(AppUser.class));

        //Asserting Method calls
        assertEquals(expectedUser, actualUser);
    }
}