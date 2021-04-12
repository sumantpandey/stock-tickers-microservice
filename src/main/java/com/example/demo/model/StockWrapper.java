package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.With;
import yahoofinance.Stock;

import java.time.LocalDateTime;

@AllArgsConstructor
@With
@Getter
public class StockWrapper {

    private final Stock stock;
    private final LocalDateTime lastAccessedAt;

    public StockWrapper(final Stock stock)
    {
        this.stock = stock;
        this.lastAccessedAt = LocalDateTime.now();
    }
    public Stock getStock(){
        return this.stock;
    }

    public LocalDateTime getLastAccessedAt(){
        return this.lastAccessedAt;
    }

}
