package com.itau.itau.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.itau.itau.DTO.StatisticsDTO;
import com.itau.itau.DTO.TransactionsDTO;
import com.itau.itau.service.TransactionsService;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;




@RestController
@RequestMapping("/transacao")
public class TransactionsController {
    public final TransactionsService service;
    
    public TransactionsController(TransactionsService service) {
        this.service = service;
    }

    @PostMapping()
    public ResponseEntity<Void> postMethodName(@RequestBody TransactionsDTO dto) {
        service.save(dto);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
    
    @DeleteMapping()
    public ResponseEntity<Void> deleteMethodName() {
        service.deleteAll();
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("/estatistica")
    public ResponseEntity<StatisticsDTO> transactionStatics() {
       return new ResponseEntity<StatisticsDTO>(service.getStatistics(), HttpStatus.OK);
    }
    
}
