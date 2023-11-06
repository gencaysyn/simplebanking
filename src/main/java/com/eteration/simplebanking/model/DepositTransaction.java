package com.eteration.simplebanking.model;

import lombok.Getter;

@Getter
public class DepositTransaction  extends Transaction{
    public DepositTransaction(double amount){
        super(amount);
    }

    @Override
    public void execute(Account account) throws InsufficientBalanceException {
        account.deposit(super.getAmount());
    }


}
