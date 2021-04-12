package com.example.demo;

import com.example.demo.exception.StockNotFoundException;
import com.example.demo.model.StockWrapper;
import com.example.demo.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockController {

    @Autowired
    private StockService stockservice;

    @GetMapping("/{ticker}")
    public ResponseEntity<StockWrapper> getStock(@PathVariable("ticker") String ticker) {

        StockWrapper stock = stockservice.findStock(ticker);

        if(stock.getStock() == null) {
            throw new StockNotFoundException("Invalid ticker symbol : " + ticker);
        }
        return new ResponseEntity<StockWrapper>(stock, HttpStatus.OK);

    }
}
