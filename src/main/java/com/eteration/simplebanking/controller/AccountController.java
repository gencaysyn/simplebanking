package com.eteration.simplebanking.controller;

import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.Transaction;
import org.springframework.http.ResponseEntity;

public class AccountController {


    public ResponseEntity<Account> getAccount(String accountNumber) {
        return null;
    }

    public ResponseEntity<TransactionStatus> credit(String accountNumber, Transaction transaction) {
        return null;
    }
    public ResponseEntity<TransactionStatus> debit(String accountNumber, Transaction transaction) {
        return null;
	}
}