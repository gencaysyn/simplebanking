package com.eteration.simplebanking.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("DepositTransaction")
public class DepositTransaction extends Transaction {

    public DepositTransaction(){
        super();
    }
    public DepositTransaction(double amount) {
        super(amount);
    }

    @Override
    public void execute(BankAccount bankAccount) {
        bankAccount.deposit(super.getAmount());
    }
    public String getType() {
        return "DepositTransaction";
    }
}
