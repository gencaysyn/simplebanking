package com.eteration.simplebanking.controller;

import com.eteration.simplebanking.model.*;
import com.eteration.simplebanking.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/account/v1")
public class AccountController {
    @Autowired
    public AccountService accountService;

    @GetMapping("/{accountNumber}")
    public ResponseEntity<Account> getAccount(@PathVariable String accountNumber) {
        Account account = accountService.findAccount(accountNumber);
        if(account != null){
            return new ResponseEntity<>(account, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/credit/{accountNumber}")
    public ResponseEntity<TransactionStatus> credit(@PathVariable String accountNumber, @RequestBody Transaction transaction) throws InsufficientBalanceException {
        Account account = accountService.findAccount(accountNumber);
        if(account == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        account.post(new DepositTransaction(transaction.getAmount()));
        return new ResponseEntity<>(new TransactionStatus("OK", UUID.randomUUID().toString()), HttpStatus.OK);

    }
    @PostMapping("/debit/{accountNumber}")
    public ResponseEntity<TransactionStatus> debit(String accountNumber, Transaction transaction) throws InsufficientBalanceException {
        Account account = accountService.findAccount(accountNumber);
        if(account == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        account.post(new WithdrawalTransaction(transaction.getAmount()));
        return new ResponseEntity<>(new TransactionStatus("OK", UUID.randomUUID().toString()), HttpStatus.OK);
	}
}