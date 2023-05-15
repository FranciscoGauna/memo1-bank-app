package com.aninfo.service;

import com.aninfo.model.Transaction;
import com.aninfo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    public void createTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    public List<Transaction> getTransactionHistory(Long cbu) {
        return transactionRepository.findTransactionByCbu(cbu);
    }

    public void deleteById(Long ID) {
        transactionRepository.deleteById(ID);
    }

    public Optional<Transaction> getTransaction(Long ID) {
        return transactionRepository.findTransactionByID(ID);
    }
}
