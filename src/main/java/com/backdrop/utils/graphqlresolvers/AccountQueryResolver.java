package com.backdrop.utils.graphqlresolvers;

import com.backdrop.data.models.AppUser;
import com.backdrop.data.repository.AppUserRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AccountQueryResolver implements GraphQLQueryResolver {
    private final AppUserRepository userRepository;

    public AppUser getAccountName(String accountNumber, String bankCode){
        return userRepository.findByAccountNumberAndBankCode(accountNumber, bankCode).orElse(null);
    }
}