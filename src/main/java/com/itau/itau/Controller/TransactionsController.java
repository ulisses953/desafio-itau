package com.itau.itau.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.itau.itau.DTO.StatisticsDTO;
import com.itau.itau.DTO.TransactionsDTO;
import com.itau.itau.service.TransactionsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

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

    @Operation(summary = "Create a transaction")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Transaction created"),
        @ApiResponse(responseCode = "422", description = "Invalid transaction data"),
        @ApiResponse(responseCode = "400", description = "Internal server error")
    })
    @PostMapping()
    public ResponseEntity<Void> postMethodName(@RequestBody TransactionsDTO dto) {
        service.save(dto);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
    @Operation(summary = "Delete all transactions")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "All transactions deleted"),
        @ApiResponse(responseCode = "400", description = "Internal server error")
    })
    @DeleteMapping()
    public ResponseEntity<Void> deleteMethodName() {
        service.deleteAll();
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @Operation(summary = "Get transaction statistics")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Transaction statistics",content = @Content(mediaType = "application/json",schema = @Schema(implementation = StatisticsDTO.class))),
        @ApiResponse(responseCode = "400", description = "Internal server error",content = @Content)
    })
    @GetMapping("/estatistica")
    public ResponseEntity<StatisticsDTO> transactionStatics() {
       return new ResponseEntity<StatisticsDTO>(service.getStatistics(), HttpStatus.OK);
    }
    
}
