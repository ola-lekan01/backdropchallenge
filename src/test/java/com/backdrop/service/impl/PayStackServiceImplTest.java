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
        // Setting up Variables
        String accountName = "Sofuyi Olalekan Samson";
        String accountNumber = "0108510992";
        String bankCode = "058";

        // Making a method call
        Data accountData = payStackService.getAccountName(accountNumber, bankCode);

        // Asserting Responses
        assertEquals(accountName.toUpperCase(), accountData.getAccount_name());
    }
}