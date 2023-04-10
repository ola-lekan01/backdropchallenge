package com.backdrop.service.impl;

import com.backdrop.data.models.AppUser;
import com.backdrop.utils.graphqlresolvers.AccountMutationResolver;
import com.backdrop.utils.graphqlresolvers.AccountQueryResolver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class GraphQLServiceImplTest {
    @Mock
    private AccountQueryResolver queryResolverMock;
    @Mock
    private AccountMutationResolver mutationResolverMock;
    @InjectMocks
    private GraphQLServiceImpl graphQLServiceMock;
    private final String ACCOUNT_NAME = "Sofuyi Olalekan Samson";
    private final String ACCOUNT_NUMBER = "0108510992";
    private final String BANK_CODE = "058";
    private final Boolean IS_VERIFIED = true;
    private AppUser expectedAppUser;

    @BeforeEach
    void setUp(){
        // Setting up variables

        expectedAppUser = new AppUser();
        expectedAppUser.setAccountName(ACCOUNT_NAME);
        expectedAppUser.setAccountNumber(ACCOUNT_NUMBER);
        expectedAppUser.setBankCode(BANK_CODE);
        expectedAppUser.setIsVerified(IS_VERIFIED);
    }

    @Test
    public void testGetAccountName() {
        // Setting up repository Mocks
        when(queryResolverMock.getAccountName(ACCOUNT_NUMBER, BANK_CODE)).thenReturn(expectedAppUser);

        // Act
        AppUser result = graphQLServiceMock.getAccountName(ACCOUNT_NUMBER, BANK_CODE);

        // Assert
        assertEquals(expectedAppUser, result);

        // verify repository method calls
        verify(queryResolverMock).getAccountName(ACCOUNT_NUMBER, BANK_CODE);
    }

    @Test
    public void testAddAccountUser() {
        // Setting up repository Mocks
        when(mutationResolverMock.addAccountUser(ACCOUNT_NAME, ACCOUNT_NUMBER, BANK_CODE, IS_VERIFIED)).thenReturn(expectedAppUser);
        // Act
        AppUser result = graphQLServiceMock.addAccountUser(ACCOUNT_NAME, ACCOUNT_NUMBER, BANK_CODE, IS_VERIFIED);

        // verifying response are as expected
        assertEquals(expectedAppUser, result);

        // verify repository method calls
        verify(mutationResolverMock).addAccountUser(ACCOUNT_NAME, ACCOUNT_NUMBER, BANK_CODE, IS_VERIFIED);
    }
}