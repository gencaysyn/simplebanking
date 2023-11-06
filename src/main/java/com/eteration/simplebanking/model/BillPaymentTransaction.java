package com.eteration.simplebanking.model;

import java.time.LocalDateTime;

public class BillPaymentTransaction extends Transaction{
    private String payee;
    public BillPaymentTransaction(double amount) {
        super(amount);
    }
}
