package com.itau.itau.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itau.itau.Entity.Transactions;

@Repository
public interface TransactionsRepository extends JpaRepository<Transactions,UUID>{
    
}
