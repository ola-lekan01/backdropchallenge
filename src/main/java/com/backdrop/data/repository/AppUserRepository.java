package com.backdrop.data.repository;

import com.backdrop.data.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, String> {
    Optional<AppUser> findByAccountNumberAndBankCode(String accountNumber, String bankCode);
}
