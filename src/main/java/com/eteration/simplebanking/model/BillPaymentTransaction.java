package com.eteration.simplebanking.model;


public class BillPaymentTransaction extends Transaction {
    private final String payee;

    public BillPaymentTransaction(String payee, double amount) {
        super(amount);
        this.payee = payee;
    }

    @Override
    public String toString() {
        return "BillPaymentTransaction{" + "payee='" + payee + '\'' + '}';
    }

    @Override
    public void execute(Account account) throws InsufficientBalanceException {
        account.withdraw(getAmount());
    }
}
