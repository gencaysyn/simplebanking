package com.eteration.simplebanking.controller;

import com.eteration.simplebanking.model.*;
import com.eteration.simplebanking.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account/v1")
public class AccountController {
    @Autowired
    public AccountService accountService;

    @GetMapping("/{accountNumber}")
    public ResponseEntity<BankAccount> getAccount(@PathVariable String accountNumber) {
        BankAccount bankAccount = accountService.findAccount(accountNumber);
        if(bankAccount != null){
            return new ResponseEntity<>(bankAccount, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/credit/{accountNumber}")
    public ResponseEntity<TransactionStatus> credit(@PathVariable String accountNumber, @RequestBody TransactionRequest tr) throws InsufficientBalanceException {
        BankAccount bankAccount = accountService.findAccount(accountNumber);
        if(bankAccount == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        bankAccount.post(new DepositTransaction(tr.getAmount()));
        accountService.saveAccount(bankAccount);
        return new ResponseEntity<>(new TransactionStatus("OK", bankAccount.getTransactions().get(bankAccount.getTransactions().size()-1).getApprovalCode()), HttpStatus.OK);

    }
    @PostMapping("/debit/{accountNumber}")
    public ResponseEntity<TransactionStatus> debit(@PathVariable String accountNumber,  @RequestBody TransactionRequest tr) throws InsufficientBalanceException {
        BankAccount bankAccount = accountService.findAccount(accountNumber);
        if(bankAccount == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        bankAccount.post(new WithdrawalTransaction(tr.getAmount()));
        accountService.saveAccount(bankAccount);
        return new ResponseEntity<>(new TransactionStatus("OK", bankAccount.getTransactions().get(bankAccount.getTransactions().size()-1).getApprovalCode()), HttpStatus.OK);
	}
}