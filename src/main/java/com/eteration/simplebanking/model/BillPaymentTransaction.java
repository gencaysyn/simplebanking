package com.eteration.simplebanking.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("BillPaymentTransaction")
public class BillPaymentTransaction extends Transaction {
    private String payee;

    public BillPaymentTransaction() {
    }

    public BillPaymentTransaction(String payee, double amount) {
        super(amount);
        this.payee = payee;
    }

    public String getType(){
        return "BillPaymentTransaction";
    }

    @Override
    public String toString() {
        return "BillPaymentTransaction{" + "payee='" + payee + '\'' + '}';
    }

    @Override
    public void execute(BankAccount bankAccount) throws InsufficientBalanceException {
        bankAccount.withdraw(getAmount());
    }
}
