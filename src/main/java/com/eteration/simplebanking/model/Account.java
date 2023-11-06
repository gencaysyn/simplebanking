package com.eteration.simplebanking.model;


import lombok.Data;

import java.util.ArrayList;

@Data
public class Account {
    private String owner;
    private String accountNumber;
    private double balance;
    private ArrayList<Transaction> transactions;

    public Account(String owner, String accountNumber) {
        this.owner = owner;
        this.accountNumber = accountNumber;
        this.balance = 0.0;
        this.transactions = new ArrayList<>();
    }

    public void credit(double amount) {
        balance += amount;
    }

    public void debit(double amount) throws InsufficientBalanceException {
        if (balance >= amount) {
            balance -= amount;
        } else {
            throw new InsufficientBalanceException();
        }
    }

    public void post(Transaction transaction) {
        transaction.execute(this);
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) throws InsufficientBalanceException {
        if (balance >= amount) {
            balance -= amount;
        } else {
            throw new InsufficientBalanceException();
        }
    }
}
