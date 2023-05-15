package com.aninfo.repository;

import com.aninfo.model.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    Optional<Transaction> findTransactionByID(Long id);
    List<Transaction> findTransactionByCbu(Long cbu);

    @Override
    List<Transaction> findAll();

}
