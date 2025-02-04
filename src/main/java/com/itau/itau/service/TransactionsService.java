package com.itau.itau.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.itau.itau.DTO.TransactionsDTO;
import com.itau.itau.Repository.TransactionsRepository;
import com.itau.itau.utils.Converter;

@Service
public class TransactionsService {
    private final TransactionsRepository repository;

    public TransactionsService(TransactionsRepository repository) {
        this.repository = repository;
    }

    public UUID save(TransactionsDTO dto){
        return repository.save(Converter.transactionsDTOToTransactions(dto)).getId();
    }
}
