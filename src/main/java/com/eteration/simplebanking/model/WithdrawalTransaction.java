package com.eteration.simplebanking.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("WithdrawalTransaction")
public class WithdrawalTransaction extends Transaction{
    public WithdrawalTransaction() {
    }

    public WithdrawalTransaction(double amount){
        super(amount);
    }

    @Override
    public void execute(BankAccount bankAccount) throws InsufficientBalanceException {
        bankAccount.withdraw(getAmount());
    }
    public String getType() {
        return "WithdrawalTransaction";
    }
}


