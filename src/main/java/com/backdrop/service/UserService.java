package com.backdrop.service;

import com.backdrop.data.models.AppUser;

import java.io.IOException;

public interface UserService {
    AppUser addUserAccount(String accountName, String accountNumber, String bankCode) throws IOException;
    AppUser getAccountName(String accountNumber, String bankCode) throws IOException;
}
