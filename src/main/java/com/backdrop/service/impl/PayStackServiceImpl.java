package com.backdrop.service.impl;

import com.backdrop.data.dtos.Account.Account;
import com.backdrop.data.dtos.Account.Data;
import com.backdrop.service.PayStackService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.ResponseBody;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PayStackServiceImpl implements PayStackService {
    private final String secretKey = System.getenv("PAY_STACK_SECRET_KEY");
    private static final String PAYSTACK_RESOLVE_URL = "https://api.paystack.co/bank/resolve";
    private final ObjectMapper mapper = new ObjectMapper();
    @Override
    public Data getAccountName(String accountNumber, String bankCode) throws IOException {
        OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(PAYSTACK_RESOLVE_URL).newBuilder();
        urlBuilder.addQueryParameter("account_number", accountNumber);
        urlBuilder.addQueryParameter("bank_code", bankCode);

        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .get()
                .header("Authorization", "Bearer " + secretKey)
                .build();

        try(ResponseBody responseBody = client.newCall(request).execute().body()){
            return mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                    .readValue(responseBody.string(), Account.class).getData();
        }
    }
}
