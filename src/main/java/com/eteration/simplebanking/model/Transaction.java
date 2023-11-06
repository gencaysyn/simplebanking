package com.eteration.simplebanking.model;




import java.time.LocalDateTime;


public abstract class Transaction {
	private LocalDateTime date;
    private double amount;

    public Transaction(double amount) {
        this.date = LocalDateTime.now();
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "date=" + date +
                ", amount=" + amount +
                '}';
    }

    public abstract void execute(Account account) throws InsufficientBalanceException;
}
