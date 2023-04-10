package com.backdrop.service.impl;

import com.backdrop.data.models.AppUser;
import com.backdrop.service.GraphQLService;
import com.backdrop.utils.graphqlresolvers.AccountMutationResolver;
import com.backdrop.utils.graphqlresolvers.AccountQueryResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GraphQLServiceImpl implements GraphQLService {
    private final AccountQueryResolver queryResolver;
    private final AccountMutationResolver mutationResolver;

    @Override
    public AppUser getAccountName(String accountNumber, String bankCode){
        return queryResolver.getAccountName(accountNumber, bankCode);
    }

    @Override
    public AppUser addAccountUser(String accountName, String accountNumber, String bankCode, Boolean isVerified) {
        return mutationResolver.addAccountUser(accountName, accountNumber, bankCode, isVerified);
    }
}
