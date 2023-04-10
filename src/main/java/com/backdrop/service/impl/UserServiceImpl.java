package com.backdrop.service.impl;

import com.backdrop.data.dtos.Account.Data;
import com.backdrop.data.models.AppUser;
import com.backdrop.service.GraphQLService;
import com.backdrop.service.PayStackService;
import com.backdrop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;

import static com.backdrop.utils.StringUtils.getLevenshteinDistance;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final PayStackService payStackService;
    private final GraphQLService graphQLService;
    @Override
    public AppUser addUserAccount(String accountName, String accountNumber, String bankCode) throws IOException {
        Data accountData = payStackService.getAccountName(accountNumber, bankCode);
        String payStackName = accountData.getAccount_name().toLowerCase();
        int distance = getLevenshteinDistance(accountName.toLowerCase(), payStackName);
        boolean isVerified = distance <= 2;

        AppUser user;
        if (isVerified) {
            user = graphQLService.addAccountUser(accountName.toLowerCase(), accountNumber, bankCode, true);
        }
        else {
            user = graphQLService.addAccountUser(payStackName.toLowerCase(), accountNumber, bankCode, false);
        }
        return user;
    }

    @Override
    public AppUser getAccountName(String accountNumber, String bankCode) throws IOException {
        AppUser user = graphQLService.getAccountName(accountNumber, bankCode);

        if(Objects.nonNull(user)){
            return user;
        }
        String accountName = "null";
        return addUserAccount(accountName, accountNumber, bankCode);
    }
}
