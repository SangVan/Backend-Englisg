package com.example.apienglish.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.apienglish.entity.Account;

import java.util.Optional;

public interface EmailServiceRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByEmailAndVerificationCode(String email, String code);
}
