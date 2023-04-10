package com.backdrop.service;

import com.backdrop.data.models.AppUser;

public interface GraphQLService {
    AppUser getAccountName(String accountName, String bankCode);

    AppUser addAccountUser(String accountName, String accountNumber, String bankCode, Boolean isVerified) ;
}
