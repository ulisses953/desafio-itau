package com.itau.itau.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
    
    private final int timeInSeconds;

    public TransactionsService(TransactionsRepository repository, 
    @Value("${custom.transaction.statistics.timeInSeconds}") int timeInSeconds) {
        this.repository = repository;
        this.timeInSeconds = timeInSeconds;
    }

    public UUID save(TransactionsDTO dto){

        if (dto.date() == null || dto.value() == null) {
            throw new IllegalArgumentException("Date and value are required");
        }

        if (dto.date().getTime() >= new Date().getTime()) {
            throw new IllegalArgumentException("Date cannot be in the past");
        }

        if (dto.value() < 0) {
            throw new IllegalArgumentException("Value must be non-negative");
        }

        return repository.save(Converter.transactionsDTOToTransactions(dto)).getId();
    }

    public List<UUID> deleteAll() {
        List<UUID> ids = getIds(repository.findAll());
        repository.deleteAll();
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
        Date dateLimit = getTimer();

        long count = repository.count(dateLimit);
        Double sum = repository.sum(dateLimit);
        Double avg = repository.avg(dateLimit);
        Double min = repository.min(dateLimit);
        Double max = repository.max(dateLimit);


        return new StatisticsDTO(
                count,
                sum != null ? sum : 0.0,
                avg != null ? avg : 0.0,
                min != null ? min : 0.0,
                max != null ? max : 0.0
        );
    }

    private Date getTimer() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, - timeInSeconds);
        return calendar.getTime();
    }
        
}
