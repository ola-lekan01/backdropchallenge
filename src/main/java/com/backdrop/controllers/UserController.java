package com.backdrop.controllers;

import com.backdrop.data.models.AppUser;
import com.backdrop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @MutationMapping
    public AppUser addAccountUser(@Argument String accountName, @Argument String accountNumber,
                                  @Argument String bankCode) throws IOException {
        return userService.addUserAccount(accountName, accountNumber, bankCode);
    };

    @QueryMapping
    public AppUser getAccountName(@Argument String accountNumber,
                                  @Argument String bankCode) throws IOException {
        return userService.getAccountName(accountNumber, bankCode);
    }
}
