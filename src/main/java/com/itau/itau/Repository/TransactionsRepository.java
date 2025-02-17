package com.itau.itau.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.itau.itau.Entity.Transactions;

@Repository@EnableJpaRepositories
public interface TransactionsRepository extends JpaRepository<Transactions,UUID>{
    @Query("SELECT COUNT(t) FROM Transactions t WHERE t.date >= :dateLimit")
    long count(LocalDateTime dateLimit);

    @Query("SELECT SUM(t.value) FROM Transactions t WHERE t.date >= :dateLimit")
    Double sum(LocalDateTime dateLimit);

    @Query("SELECT AVG(t.value) FROM Transactions t WHERE t.date >= :dateLimit")
    Double avg(LocalDateTime dateLimit);

    @Query("SELECT MIN(t.value) FROM Transactions t WHERE t.date >= :dateLimit")
    Double min(LocalDateTime dateLimit);

    @Query("SELECT MAX(t.value) FROM Transactions t WHERE t.date >= :dateLimit")
    Double max(LocalDateTime dateLimit);
}
