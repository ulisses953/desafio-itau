package com.itau.itau.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.itau.itau.DTO.TransactionsDTO;
import com.itau.itau.service.TransactionsService;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class TransactionsController {
    public final TransactionsService service;
    
    public TransactionsController(TransactionsService service) {
        this.service = service;
    }

    @PostMapping("transacao")
    public ResponseEntity<Void> postMethodName(@RequestBody TransactionsDTO dto) {
        service.save(dto);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
    
    @GetMapping("teste")
    public String getMethodName() {
        return "Deu Certo";
    }
    
}
