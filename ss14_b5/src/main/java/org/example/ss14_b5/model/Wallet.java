package org.example.ss14_b5.model;

import jakarta.persistence.*;

import java.lang.ScopedValue;

@Entity
public class Wallet {
    @Id
    private Long customerId;
    private double balance;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

}

