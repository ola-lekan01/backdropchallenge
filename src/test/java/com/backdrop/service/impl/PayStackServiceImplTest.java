package com.backdrop.service.impl;

import com.backdrop.data.dtos.Account.Data;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PayStackServiceImplTest {
    private final PayStackServiceImpl payStackService = new PayStackServiceImpl();
    // Kindly pass in the Paystack secret key in the class implementation
    @Test
    void testThatNameFetchedByAPIIsReturnedIfUserDoesntProvideAnAccountName() throws IOException {
        String accountName = "Sofuyi Olalekan Samson";
        String accountNumber = "0108510992";
        String bankCode = "058";

        Data accountData = payStackService.getAccountName(accountNumber, bankCode);
        assertEquals(accountName.toUpperCase(), accountData.getAccount_name());
    }
}