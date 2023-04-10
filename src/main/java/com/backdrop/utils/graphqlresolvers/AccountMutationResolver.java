package com.backdrop.utils.graphqlresolvers;

import com.backdrop.data.models.AppUser;
import com.backdrop.data.repository.AppUserRepository;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AccountMutationResolver implements GraphQLMutationResolver {

    private final AppUserRepository userRepository;

    public AppUser addAccountUser(String accountName, String accountNumber, String bankCode, Boolean isVerified){
        return userRepository.save(AppUser.builder()
                .accountName(accountName)
                .accountNumber(accountNumber)
                .bankCode(bankCode)
                .isVerified(isVerified)
                .build());
    }
}
