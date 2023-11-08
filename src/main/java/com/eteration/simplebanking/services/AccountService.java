package com.eteration.simplebanking.services;


import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    public Account findAccount(String accountNumber) {
        return accountRepository.getById(accountNumber);
    }
}
