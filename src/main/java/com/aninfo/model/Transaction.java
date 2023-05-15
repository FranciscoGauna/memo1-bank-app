package com.aninfo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long ID;

    long cbu;

    double amount;

    TransactionType type;

    public Transaction(double depositAmount, Long accountCbu, TransactionType transactionType) {
        amount = depositAmount;
        cbu = accountCbu;
        type = transactionType;
    }

    public Transaction() {}

    public TransactionType getType() {
        return type;
    }

    public Long getID() {
        return ID;
    }
    public Long getCbu() {
        return cbu;
    }
    public double getAmount() {
        return amount;
    }
}
