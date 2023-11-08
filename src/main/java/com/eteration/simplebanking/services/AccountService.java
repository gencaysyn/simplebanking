package com.eteration.simplebanking.services;


import com.eteration.simplebanking.model.BankAccount;
import com.eteration.simplebanking.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    public BankAccount findAccount(String accountNumber) {
        return accountRepository.findById(accountNumber).orElse(null);
    }

    public void saveAccount(BankAccount bankAccount){
        accountRepository.save(bankAccount);
    }
}
