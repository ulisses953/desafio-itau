package com.itau.itau.utils;

import com.itau.itau.DTO.TransactionsDTO;
import com.itau.itau.Entity.Transactions;

public class Converter {
    public static Transactions transactionsDTOToTransactions(TransactionsDTO transactionsDTO){
        return new Transactions(transactionsDTO.value(), transactionsDTO.date());
    }
         
    
}
