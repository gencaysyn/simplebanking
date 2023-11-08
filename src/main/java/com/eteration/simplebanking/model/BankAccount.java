package com.eteration.simplebanking.model;




import org.springframework.data.annotation.CreatedDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
public class BankAccount {
    private String owner;
    @Id
    private String accountNumber;
    private double balance;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Transaction> transactions;

    @CreatedDate
    private Date createDate;

    public BankAccount(){}

    public BankAccount(String owner, String accountNumber, Date createDate) {
        this.owner = owner;
        this.accountNumber = accountNumber;
        this.balance = 0.0;
        this.createDate = createDate;
        this.transactions = new ArrayList<>();
    }

    public BankAccount(String owner, String accountNumber) {
        this.owner = owner;
        this.accountNumber = accountNumber;
        this.balance = 0.0;
        this.transactions = new ArrayList<>();
    }

    public void credit(double amount) {
        balance += amount;
    }

    public void post(Transaction transaction) throws InsufficientBalanceException {
        transaction.execute(this);
        transaction.setApprovalCode(UUID.randomUUID().toString());
        transactions.add(transaction);
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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Date getCreateDate() {
        return createDate;
    }
}
