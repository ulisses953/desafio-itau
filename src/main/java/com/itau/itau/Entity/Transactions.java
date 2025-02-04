package com.itau.itau.Entity;

import java.sql.Date;
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
    public Date date;
    
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
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    
    public Transactions(UUID id, Double value, Date date) {
        this.id = id;
        this.value = value;
        this.date = date;
    }
    
    public Transactions() {
    }
    public Transactions(Double value, Date date) {
        this.value = value;
        this.date = date;
    }

    
    
}
