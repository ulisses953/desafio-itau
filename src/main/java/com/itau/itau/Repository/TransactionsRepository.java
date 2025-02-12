package com.itau.itau.Repository;

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
    long count(Date dateLimit);

    @Query("SELECT SUM(t.value) FROM Transactions t WHERE t.date >= :dateLimit")
    Double sum(Date dateLimit);

    @Query("SELECT AVG(t.value) FROM Transactions t WHERE t.date >= :dateLimit")
    Double avg(Date dateLimit);

    @Query("SELECT MIN(t.value) FROM Transactions t WHERE t.date >= :dateLimit")
    Double min(Date dateLimit);

    @Query("SELECT MAX(t.value) FROM Transactions t WHERE t.date >= :dateLimit")
    Double max(Date dateLimit);
}
