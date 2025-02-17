package com.itau.itau.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.itau.itau.DTO.StatisticsDTO;
import com.itau.itau.DTO.TransactionsDTO;
import com.itau.itau.Entity.Transactions;
import com.itau.itau.Repository.TransactionsRepository;
import com.itau.itau.utils.Converter;

@Service
public class TransactionsService {
    private final TransactionsRepository repository;

    private static final Logger logger = LoggerFactory.getLogger(TransactionsService.class);
    
    private final int timeInSeconds;

    private final ZoneId zoneId = ZoneId.of("America/Sao_Paulo");

    public TransactionsService(TransactionsRepository repository, @Value("${custom.transaction.statistics.timeInSeconds}") int timeInSeconds) {
        this.repository = repository;
        this.timeInSeconds = timeInSeconds;
        logger.info("TimeInSeconds: " + timeInSeconds);
    }

    public UUID save(TransactionsDTO dto){

        if (dto.date() == null || dto.value() == null) {
            throw new IllegalArgumentException("Date and value are required");
        }

        if (dto.date().isAfter(LocalDateTime.now(zoneId))) {
            throw new IllegalArgumentException("Date cannot be in the future");
        }

        if (dto.value() < 0) {
            throw new IllegalArgumentException("Value must be non-negative");
        }

        UUID transactionId = repository.save(Converter.transactionsDTOToTransactions(dto)).getId();
        logger.info("Transaction saved with ID: " + transactionId);
        return transactionId;
    }

    public List<UUID> deleteAll() {
        List<UUID> ids = getIds(repository.findAll());
        repository.deleteAll();
        logger.info("All transactions deleted");
        return ids;
    }

    private List<UUID> getIds(List<Transactions> transactions) {
        List<UUID> ids = new ArrayList<>();
        for (Transactions transaction : transactions) {
            ids.add(transaction.getId());
        }
        return ids;
    }

    public StatisticsDTO getStatistics() {
        LocalDateTime dateLimit = getTimer();

        long count = repository.count(dateLimit);
        Double sum = repository.sum(dateLimit);
        Double avg = repository.avg(dateLimit);
        Double min = repository.min(dateLimit);
        Double max = repository.max(dateLimit);

        logger.info("Statistics calculated: count=" + count + ", sum=" + sum + ", avg=" + avg + ", min=" + min + ", max=" + max);

        return new StatisticsDTO(
                count,
                sum != null ? sum : 0.0,
                avg != null ? avg : 0.0,
                min != null ? min : 0.0,
                max != null ? max : 0.0
        );
    }

    private LocalDateTime getTimer() {
        LocalDateTime now = LocalDateTime.now(zoneId);
        LocalDateTime dateLimit = now.minusSeconds(timeInSeconds);
        logger.info("Date limit for statistics: " + dateLimit);
        return dateLimit;
    }
}
