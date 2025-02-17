package com.itau.itau.Entity;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID id;
    public Double value;
    public LocalDateTime date;
    
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public Double getValue() {
        return value;
    }
    public void setValue(Double value) {
        this.value = value;
    }
    public LocalDateTime getDate() {
        return date;
    }
    public void setDate(LocalDateTime date) {
        this.date = date;
    }
    
    public Transactions(UUID id, Double value, LocalDateTime date) {
        this.id = id;
        this.value = value;
        this.date = date;
    }
    
    public Transactions() {
    }
    public Transactions(Double value, LocalDateTime date) {
        this.value = value;
        this.date = date;
    }

    
    
}
