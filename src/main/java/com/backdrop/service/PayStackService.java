package com.backdrop.service;

import com.backdrop.data.dtos.Account.Data;

import java.io.IOException;

public interface PayStackService {
    Data getAccountName(String accountNumber, String bankCode) throws IOException;
}
